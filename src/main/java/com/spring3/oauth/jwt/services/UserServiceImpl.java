package com.spring3.oauth.jwt.services;

import com.spring3.oauth.jwt.dtos.*;
import com.spring3.oauth.jwt.models.RefreshToken;
import com.spring3.oauth.jwt.models.UserInfo;
import com.spring3.oauth.jwt.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    TokenBlacklistService tokenBlacklistService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RefreshTokenService refreshTokenService;

    ModelMapper modelMapper = new ModelMapper();



    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        if(userRequest.getUsername() == null){
            throw new RuntimeException("Parameter username is not found in request..!!");
        } else if(userRequest.getPassword() == null){
            throw new RuntimeException("Parameter password is not found in request..!!");
        }

        UserInfo savedUser = null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = userRequest.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        UserInfo user = modelMapper.map(userRequest, UserInfo.class);
        user.setPassword(encodedPassword);
        if(userRequest.getId() != null){
            UserInfo oldUser = userRepository.findFirstById(userRequest.getId());
            if(oldUser != null){
                oldUser.setId(user.getId());
                oldUser.setPassword(user.getPassword());
                oldUser.setUsername(user.getUsername());
                oldUser.setRoles(user.getRoles());

                savedUser = userRepository.save(oldUser);
            } else {
                throw new RuntimeException("Can't find record with identifier: " + userRequest.getId());
            }
        } else {
            savedUser = userRepository.save(user);
        }
        UserResponse userResponse = modelMapper.map(savedUser, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getLoggedInUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) authentication.getPrincipal();
        String usernameFromAccessToken = userDetail.getUsername();
        UserInfo user = userRepository.findByUsername(usernameFromAccessToken);
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        return userResponse;
    }

    @Override
    public UserResponse getUserById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        UserInfo user = userRepository.findFirstById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }

        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public List<UserResponse> getAllUser() {
        List<UserInfo> users = (List<UserInfo>) userRepository.findAll();
        Type setOfDTOsType = new TypeToken<List<UserResponse>>(){}.getType();
        List<UserResponse> userResponses = modelMapper.map(users, setOfDTOsType);
        return userResponses;
    }

    @Override
    public Long deleteUserById(Long id) {
        if (id != null) {
            UserInfo user = userRepository.findFirstById(id);
            if (user != null) {
                userRepository.delete(user);
            } else {
                throw new RuntimeException("User not found with id: " + id);
            }
        } else {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return id;
    }

    @Override
    public JwtResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()){
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken()).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @Override
    public JwtResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.GenerateToken(userInfo.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @Override
    public String logout(HttpServletRequest request) {
        tokenBlacklistService.addToBlacklist(request);
        // Clear any session-related data if necessary
        return "Logged out successfully";
    }


}
