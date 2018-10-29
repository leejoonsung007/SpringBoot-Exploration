package com.springboot.blog.BlogSystem.service;

import com.springboot.blog.BlogSystem.domain.Comment;

import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentById(Long id);

    void removeComment(Long id);
}
