package com.sparta.myfirstblog.repository;

import com.sparta.myfirstblog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
