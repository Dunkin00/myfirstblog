package com.sparta.myfirstblog.controller;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.entity.Post;
import com.sparta.myfirstblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }
}
