package com.sparta.myfirstblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    @PostMapping("/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return PostService.createPost(requestDto);
    }
}
