package com.example.introductory.repository;

import com.example.introductory.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends CrudRepository<Post, UUID> {

    Optional<Post> findByUuidAndClientUuid(UUID imageUuid, UUID userUuid);

    List<Post> findAllByClientUuidNot(UUID client_uuid);

    @Query(value = " select * from post as p\n" +
            " left join friend f on f.second_client_id = p.client_uuid\n" +
            " where f.first_client_id = ?1", nativeQuery = true)
    List<Post> findAllSubscribePost(UUID uuid, Pageable pageable);

}
