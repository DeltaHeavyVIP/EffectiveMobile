package com.example.introductory.repository;

import com.example.introductory.model.JwtUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JwtUserRepository extends CrudRepository<JwtUser, UUID> {

    JwtUser findByUsernameOrEmail(String username, String email);

    JwtUser findByUsernameAndEmail(String username, String email);
}
