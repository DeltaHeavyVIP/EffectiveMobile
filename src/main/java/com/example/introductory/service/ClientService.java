package com.example.introductory.service;

import com.example.introductory.model.Client;

import java.util.Set;
import java.util.UUID;

public interface ClientService {
    Set<Client> getAllSubscribe();

    Set<Client> getAllSubscriptions();

    Client addSubscribe(UUID uuid);

    void deleteSubscribe(UUID uuid);
}
