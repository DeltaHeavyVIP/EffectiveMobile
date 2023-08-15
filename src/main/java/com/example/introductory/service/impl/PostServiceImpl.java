package com.example.introductory.service.impl;

import com.example.introductory.dto.request.PostRequestDto;
import com.example.introductory.exception.PermissionDeniedException;
import com.example.introductory.exception.ResourceNotFoundException;
import com.example.introductory.model.Post;
import com.example.introductory.repository.PostRepository;
import com.example.introductory.service.PostService;
import com.example.introductory.utils.ContextHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @Transactional
    public Post getPost(UUID uuid) {
        return postRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException(String.format("Couldn't find post with UUID %s in method getPost()", uuid.toString())));
    }

    @Override
    @Transactional
    public List<Post> getAllPostsOtherPeople() {
        return postRepository.findAllByClientUuidNot(ContextHelper.getJwtUserUuidFromContext());
    }

    @Override
    @Transactional
    public List<Post> getSubscribePost(Integer page, Integer pageSize, boolean sorted) {
        Pageable pageable;
        if (sorted) {
            pageable = PageRequest.of(page, pageSize);
        } else {
            pageable = PageRequest.of(page, pageSize, Sort.by("create_date"));
        }

        return postRepository.findAllSubscribePost(ContextHelper.getJwtUserUuidFromContext(), pageable);
    }


    @Override
    @Transactional
    public Post createPost(PostRequestDto postRequestDto, MultipartFile file) throws IOException {
        return postRepository.save(new Post(null, postRequestDto.getTitle(), postRequestDto.getText(),
                file.getBytes(), ContextHelper.getClientFromContext(), new Date()));
    }

    @Override
    @Transactional
    public Post updatePost(UUID uuid, PostRequestDto postRequestDto, MultipartFile file) throws IOException {
        Post post =
                postRepository.findByUuidAndClientUuid(uuid,
                        ContextHelper.getClientUuidFromContext()).orElseThrow(() -> new PermissionDeniedException(String.format("Impossible update a post with an UUID %s because the user with UUID &s does not have permission", uuid, ContextHelper.getClientUuidFromContext().toString())));
        return postRepository.save(new Post(post.getUuid(), postRequestDto.getTitle(), postRequestDto.getText(),
                file.getBytes(), ContextHelper.getClientFromContext(), new Date()));
    }

    @Override
    @Transactional
    public void deletePost(UUID uuid) {
        Post post =
                postRepository.findByUuidAndClientUuid(uuid,
                        ContextHelper.getClientUuidFromContext()).orElseThrow(() -> new PermissionDeniedException(String.format("Impossible delete a post with an UUID %s because the user with UUID &s does not have permission", uuid, ContextHelper.getClientUuidFromContext().toString())));
        postRepository.delete(post);
    }

}
