package com.example.introductory.service;

import com.example.introductory.dto.request.PostRequestDto;
import com.example.introductory.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface PostService {
    Post getPost(UUID uuid);

    List<Post> getAllPostsOtherPeople();

    Post createPost(PostRequestDto postRequestDto, MultipartFile file) throws IOException;

    Post updatePost(UUID uuid, PostRequestDto postRequestDto, MultipartFile file) throws IOException;

    void deletePost(UUID uuid);

    List<Post> getSubscribePost(Integer page, Integer pageSize, boolean sorted);
}
