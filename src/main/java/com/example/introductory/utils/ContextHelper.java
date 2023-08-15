package com.example.introductory.utils;

import com.example.introductory.model.Client;
import com.example.introductory.model.JwtUser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.UUID;

public class ContextHelper {

    public static UUID getJwtUserUuidFromContext() {
        return getJwtUserFromContext().getUuid();
    }

    public static JwtUser getJwtUserFromContext() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }


    public static UUID getClientUuidFromContext() {
        return getJwtUserFromContext().getClient().getUuid();
    }


    public static Client getClientFromContext() {
        return getJwtUserFromContext().getClient();
    }


}
