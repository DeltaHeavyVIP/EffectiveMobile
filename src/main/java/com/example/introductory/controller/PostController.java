package com.example.introductory.controller;

import com.example.introductory.dto.request.PostRequestDto;
import com.example.introductory.model.Post;
import com.example.introductory.service.PostService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{uuid}")
    public Post getPost(@PathVariable UUID uuid) {
        return postService.getPost(uuid);
    }

    @GetMapping("/")
    public List<Post> getAllPostOtherPeople() {
        return postService.getAllPostsOtherPeople();
    }

    @GetMapping("/{page}/{pageSize}/{sorted}")
    public List<Post> getSubscribePost(@PathVariable @NotBlank Integer page,
                                            @PathVariable @NotBlank Integer pageSize,
                                            @PathVariable @NotBlank Boolean sorted) {
        return postService.getSubscribePost(page, pageSize, sorted);
    }

    @PostMapping("")
    public Post createPost(@RequestPart("post") PostRequestDto postRequestDto,
                           @RequestPart("image") MultipartFile file) throws IOException {
        return postService.createPost(postRequestDto, file);
    }

    @PutMapping("/{uuid}")
    public Post updatePost(@PathVariable UUID uuid,
                           @RequestPart("post") PostRequestDto postRequestDto,
                           @RequestPart("image") MultipartFile file) throws IOException {
        return postService.updatePost(uuid, postRequestDto, file);
    }

    @DeleteMapping("/{uuid}")
    public void deletePost(@PathVariable UUID uuid) {
        postService.deletePost(uuid);
    }

}
