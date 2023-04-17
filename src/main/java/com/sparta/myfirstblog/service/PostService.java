package com.sparta.myfirstblog.service;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.dto.PostResponseDto;
import com.sparta.myfirstblog.entity.Post;
import com.sparta.myfirstblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    public final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        return new PostResponseDto(postRepository.save(post));
    }
    @Transactional
    public List<PostResponseDto> getList() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(requestDto.getPassword().equals(post.getPassword())){
            post.update(requestDto);
        } else {
            return new PostResponseDto();
        }
        return new PostResponseDto(post);
    }

    @Transactional
    public String deletePost(Long id, String password) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        if(password.equals(post.getPassword())){
            postRepository.deleteById(id);
        } else {
            return "비밀번호가 틀립니다.";
        }
        return "삭제 성공했습니다.";
    }
}
