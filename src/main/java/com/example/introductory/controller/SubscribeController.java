package com.example.introductory.controller;


import com.example.introductory.model.Client;
import com.example.introductory.service.ClientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = "/client")
public class SubscribeController {

    private final ClientService clientService;

    public SubscribeController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/subscribe")
    public Set<Client> getAllSubscribe() {
        return clientService.getAllSubscribe();
    }

    @GetMapping("/subscription")
    public Set<Client> getAllSubscription() {
        return clientService.getAllSubscriptions();
    }

    @PostMapping("/{uuid}")
    public Client addSubscribe(@PathVariable UUID uuid) {
        return clientService.addSubscribe(uuid);
    }

    @DeleteMapping("/{uuid}")
    public void deleteSubscribe(@PathVariable UUID uuid) {
        clientService.deleteSubscribe(uuid);
    }


}
