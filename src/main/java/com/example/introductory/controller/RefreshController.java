package com.example.introductory.controller;


import com.example.introductory.configuration.security.JwtProvider;
import com.example.introductory.dto.request.RefreshRequestDto;
import com.example.introductory.dto.response.AuthorizationResponseDto;
import com.example.introductory.model.JwtUser;
import com.example.introductory.service.JwtUserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping(value = "/refresh")
public class RefreshController {

    private final JwtProvider jwtProvider;
    private final JwtUserService jwtUserService;

    public RefreshController(JwtProvider jwtProvider, JwtUserService jwtUserService) {
        this.jwtProvider = jwtProvider;
        this.jwtUserService = jwtUserService;
    }

    @PostMapping("/token")
    public AuthorizationResponseDto refreshToken(@RequestBody RefreshRequestDto refreshDto) {
        String uuid = new String(Base64.getDecoder().decode(refreshDto.getRefreshToken()));

        JwtUser jwtUser = jwtUserService.findJwtUserById(uuid);

        String token = null;
        if (jwtUser.getRefreshToken().equals(refreshDto.getRefreshToken())) {
            jwtUser = jwtUserService.updateRefreshToken(jwtUser);
            token = jwtProvider.createToken(jwtUser.getUuid().toString());
            Authentication authentication = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            throw new BadCredentialsException("Invalid refresh token");
        }

        return new AuthorizationResponseDto(jwtUser.getUuid(), token, jwtUser.getRefreshToken());
    }

}