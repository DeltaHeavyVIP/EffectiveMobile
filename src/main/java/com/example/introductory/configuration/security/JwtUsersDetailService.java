package com.example.introductory.configuration.security;

import com.example.introductory.repository.JwtUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtUsersDetailService implements UserDetailsService {
    private final JwtUserRepository jwtUserRepository;

    public JwtUsersDetailService(JwtUserRepository jwtUserRepository) {
        this.jwtUserRepository = jwtUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String uuid) throws UsernameNotFoundException {
        return jwtUserRepository.findById(UUID.fromString(uuid)).orElseThrow(() -> new UsernameNotFoundException("User with this parameter does not exists"));
    }

}