package com.spring3.oauth.jwt.controllers;

import com.spring3.oauth.jwt.dtos.*;
import com.spring3.oauth.jwt.models.RefreshToken;
import com.spring3.oauth.jwt.services.JwtService;
import com.spring3.oauth.jwt.services.RefreshTokenService;
import com.spring3.oauth.jwt.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/save")
    public ResponseEntity saveUser(@RequestBody UserRequest userRequest) {
        try {
            UserResponse userResponse = userService.saveUser(userRequest);
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        try {
            List<UserResponse> userResponses = userService.getAllUser();
            return ResponseEntity.ok(userResponses);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/profile")
    public ResponseEntity<UserResponse> getUserProfile() {
        try {
        UserResponse userResponse = userService.getLoggedInUserProfile();
        return ResponseEntity.ok().body(userResponse);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        try {
            UserResponse userResponse = userService.getUserById(id);
            return ResponseEntity.ok().body(userResponse);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(userService.deleteUserById(id));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
        try {
            return ResponseEntity.ok().body(userService.login(authRequestDTO));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDTO> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        try {
            return ResponseEntity.ok().body(userService.refreshToken(refreshTokenRequestDTO));
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/test")
    public String test() {
        try {
            return "Welcome";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Clear any session-related data if necessary
        return ResponseEntity.ok(userService.logout(request));
    }

}
