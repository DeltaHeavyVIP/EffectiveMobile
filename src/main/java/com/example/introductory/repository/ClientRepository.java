package com.example.introductory.repository;

import com.example.introductory.model.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Repository
public interface ClientRepository extends CrudRepository<Client, UUID> {

    @Query(value = "select * from client as c\n" +
            "                  LEFT JOIN friend f on c.uuid = f.first_client_id\n" +
            "where f.second_client_id = ?1", nativeQuery = true)
    Set<Client> findAllBySecondFriendUuid(UUID uuid);

    @Transactional
    @Modifying
    @Query(value = "delete from friend\n" +
            "where first_client_id = ?1 and second_client_id = ?2", nativeQuery = true)
    void deleteAllByFirstFriendUuidAndSecondFriendUuid(UUID firstFriendUuid, UUID secondFriendUuid);


}
