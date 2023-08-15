package com.example.introductory.service.impl;

import com.example.introductory.exception.ResourceNotFoundException;
import com.example.introductory.model.Client;
import com.example.introductory.repository.ClientRepository;
import com.example.introductory.service.ClientService;
import com.example.introductory.utils.ContextHelper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;


    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Set<Client> getAllSubscribe() {
        Client client =
                clientRepository.findById(ContextHelper.getClientUuidFromContext()).orElseThrow(() -> new ResourceNotFoundException(String.format("Couldn't find user with UUID %s in method getAllSubscribe()", ContextHelper.getClientUuidFromContext().toString())));
        return client.getFriend();
    }

    @Override
    public Set<Client> getAllSubscriptions() {
        return clientRepository.findAllBySecondFriendUuid(ContextHelper.getClientUuidFromContext());
    }

    @Override
    public Client addSubscribe(UUID uuid) {
        Client client =
                clientRepository.findById(ContextHelper.getClientUuidFromContext()).orElseThrow(() -> new ResourceNotFoundException(String.format("Couldn't find user with UUID %s in method addSubscribe()", ContextHelper.getClientUuidFromContext().toString())));
        client.getFriend().add(clientRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(String.format("Couldn't find user with UUID %s in method addSubscribe()", uuid.toString()))));
        return clientRepository.save(client);
    }

    @Override
    public void deleteSubscribe(UUID uuid) {
        clientRepository.deleteAllByFirstFriendUuidAndSecondFriendUuid(ContextHelper.getClientUuidFromContext(), uuid);
    }
}
