package com.example.introductory.controller;

import com.example.introductory.configuration.security.JwtProvider;
import com.example.introductory.dto.request.LoginRequestDto;
import com.example.introductory.dto.request.RegisterRequestDto;
import com.example.introductory.dto.response.AuthorizationResponseDto;
import com.example.introductory.model.JwtUser;
import com.example.introductory.service.JwtUserService;
import jakarta.persistence.NonUniqueResultException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtProvider jwtProvider;

    private final JwtUserService jwtUserService;

    public AuthController(JwtProvider jwtProvider, JwtUserService jwtUserService) {
        this.jwtProvider = jwtProvider;
        this.jwtUserService = jwtUserService;
    }

    @PostMapping("/register")
    public AuthorizationResponseDto register(@RequestBody RegisterRequestDto registerDto) {
        if (jwtUserService.findJwtUserByUserNameOrEmail(registerDto.getUsername(), registerDto.getEmail()) != null) {
            throw new NonUniqueResultException("User with this username or email has been already registered");
        }
        JwtUser jwtUser = jwtUserService.register(registerDto);
        String token = jwtProvider.createToken(jwtUser.getUuid().toString());
        SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));

        return new AuthorizationResponseDto(jwtUser.getUuid(), token, jwtUser.getRefreshToken());
    }

    @PostMapping("/login")
    public AuthorizationResponseDto login(@RequestBody LoginRequestDto loginDto) {
        JwtUser jwtUser = jwtUserService.findJwtUserByUserNameAndEmail(loginDto.getUsername(), loginDto.getEmail());
        if (jwtUser == null)
            throw new UsernameNotFoundException(String.format("User with parameter username-%s and email- %s not found",
                    loginDto.getUsername(), loginDto.getEmail()));
        String token = jwtProvider.createToken(jwtUser.getUuid().toString());
        SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(token));

        return new AuthorizationResponseDto(jwtUser.getUuid(), token, jwtUser.getRefreshToken());
    }

}
