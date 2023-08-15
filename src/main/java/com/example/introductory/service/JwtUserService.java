package com.example.introductory.service;

import com.example.introductory.dto.request.RegisterRequestDto;
import com.example.introductory.model.JwtUser;

public interface JwtUserService {

    JwtUser findJwtUserById(String uuid);

    JwtUser findJwtUserByUserNameOrEmail(String username, String email);

    JwtUser findJwtUserByUserNameAndEmail(String username, String email);

    JwtUser register(RegisterRequestDto registerDto);

    JwtUser updateRefreshToken(JwtUser jwtUser);
}
