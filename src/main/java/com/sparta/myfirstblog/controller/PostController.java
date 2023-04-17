package com.sparta.myfirstblog.controller;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    @PostMapping("/post")
    public Post createPost(@RequestBody PostRequestDto requestDto) {
        return PostService.createPost(requestDto);
    }
}
