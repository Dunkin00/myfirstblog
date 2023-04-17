package com.sparta.myfirstblog.service;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.entity.Post;
import com.sparta.myfirstblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    public final PostRepository postRepository;

    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return post;
    }
}
