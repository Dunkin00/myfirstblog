package com.sparta.myfirstblog.service;

import com.sparta.myfirstblog.dto.PostRequestDto;
import com.sparta.myfirstblog.dto.PostResponseDto;
import com.sparta.myfirstblog.entity.Post;
import com.sparta.myfirstblog.entity.User;
import com.sparta.myfirstblog.jwt.JwtUtil;
import com.sparta.myfirstblog.repository.PostRepository;
import com.sparta.myfirstblog.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private JwtUtil jwtUtil;

    //게시글 목록 조회
    @Transactional(readOnly = true)
    public List<PostResponseDto> getList() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(PostResponseDto::new).collect(Collectors.toList());
    }

    //선택한 게시글 조회
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    //게시글 등록
    public PostResponseDto createPost(PostRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }

            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = new Post(requestDto, user.getId());
            return new PostResponseDto(postRepository.save(post));
        } else {
            return null;
        }
    }

    //게시글 수정
    public PostResponseDto update(Long id, PostRequestDto requestDto, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);
        Claims claims;

        if(token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
            } else {
                throw new IllegalArgumentException("Token Error");
            }
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
            );
            Post post = postRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
            );
            post.update(requestDto);
            return new PostResponseDto(post);
        } else {
            return new PostResponseDto();
        }
    }

    //게시글 삭제
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
