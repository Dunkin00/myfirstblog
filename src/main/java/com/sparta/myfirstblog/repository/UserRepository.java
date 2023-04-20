package com.sparta.myfirstblog.repository;

import com.sparta.myfirstblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
