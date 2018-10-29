package com.springboot.blog.BlogSystem.repository;

import com.springboot.blog.BlogSystem.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
