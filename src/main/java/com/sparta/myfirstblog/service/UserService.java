package com.sparta.myfirstblog.service;

import com.sparta.myfirstblog.dto.LoginRequestDto;
import com.sparta.myfirstblog.dto.MessageDto;
import com.sparta.myfirstblog.dto.SignupRequestDto;
import com.sparta.myfirstblog.entity.StatusEnum;
import com.sparta.myfirstblog.entity.Users;
import com.sparta.myfirstblog.jwt.JwtUtil;
import com.sparta.myfirstblog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    //회원가입
    @Transactional
    public ResponseEntity signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = signupRequestDto.getPassword();

        //회원 중복 확인
        Optional<Users> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        Users user = new Users(username, password);
        userRepository.save(user);
        MessageDto messageDto = new MessageDto("회원 가입 성공!", StatusEnum.OK);
        return new ResponseEntity(messageDto, HttpStatus.OK);
    }

    //로그인
    @Transactional
    public ResponseEntity login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Users user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        if(!user.getPassword().equals(password)){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername()));
        MessageDto messageDto = new MessageDto("로그인 성공!", StatusEnum.OK);
        return new ResponseEntity(messageDto, HttpStatus.OK);
    }
}
