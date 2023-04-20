package com.sparta.myfirstblog.service;

import com.sparta.myfirstblog.dto.SignupRequestDto;
import com.sparta.myfirstblog.entity.User;
import com.sparta.myfirstblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(signupRequestDto.getUsername(), signupRequestDto.getPassword());
        userRepository.save(user);

        return "회원 가입 완료";
    }

}