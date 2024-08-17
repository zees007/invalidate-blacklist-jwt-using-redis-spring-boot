package com.spring3.oauth.jwt.services;

import com.spring3.oauth.jwt.dtos.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


public interface UserService {

    UserResponse saveUser(UserRequest userRequest);

    UserResponse getLoggedInUserProfile();

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUser();

    Long deleteUserById(Long id);

    JwtResponseDTO login(AuthRequestDTO authRequestDTO);

    JwtResponseDTO refreshToken(RefreshTokenRequestDTO refreshTokenRequestDTO);

    String logout(HttpServletRequest request);


}
