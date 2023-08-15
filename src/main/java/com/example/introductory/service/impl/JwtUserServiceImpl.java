package com.example.introductory.service.impl;

import com.example.introductory.dto.request.RegisterRequestDto;
import com.example.introductory.exception.ResourceNotFoundException;
import com.example.introductory.model.Client;
import com.example.introductory.model.JwtUser;
import com.example.introductory.repository.JwtUserRepository;
import com.example.introductory.service.JwtUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

@Service
public class JwtUserServiceImpl implements JwtUserService {

    private final JwtUserRepository jwtUserRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public JwtUserServiceImpl(JwtUserRepository jwtUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtUserRepository = jwtUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public JwtUser findJwtUserById(String uuid) {
        return jwtUserRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> new ResourceNotFoundException(String.format("Couldn't find user with UUID %s in method findJwtUserById()", uuid)));
    }

    public JwtUser findJwtUserByUserNameOrEmail(String username, String email) {
        return jwtUserRepository.findByUsernameOrEmail(username, email);
    }

    public JwtUser findJwtUserByUserNameAndEmail(String username, String email) {
        return jwtUserRepository.findByUsernameAndEmail(username, email);
    }

    public JwtUser register(RegisterRequestDto registerDto) {
        JwtUser jwtUser = new JwtUser();
        jwtUser.setUsername(registerDto.getUsername());
        jwtUser.setEmail(registerDto.getEmail());
        jwtUser.setPassword(bCryptPasswordEncoder.encode(registerDto.getPassword()));
        jwtUser.setClient(new Client());
        jwtUser = jwtUserRepository.save(jwtUser);

        jwtUser.setRefreshToken(Base64.getEncoder().encodeToString(jwtUser.getUuid().toString().getBytes()));
        return jwtUserRepository.save(jwtUser);
    }


    public JwtUser updateRefreshToken(JwtUser jwtUser) {
        jwtUser.setRefreshToken(Base64.getEncoder().encodeToString(jwtUser.getUuid().toString().getBytes()));
        return jwtUserRepository.save(jwtUser);
    }
}