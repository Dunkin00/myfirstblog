package com.sparta.myfirstblog.controller;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.dto.PostResponseDto;
import com.sparta.myfirstblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> getList() {
        return postService.getList();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id){
        return postService.getPost(id);
    }


    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto){
        return postService.update(id, requestDto);
    }

    @DeleteMapping("/post/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody PostRequestDto password){
        return postService.deletePost(id, password.getPassword());
    }
}
