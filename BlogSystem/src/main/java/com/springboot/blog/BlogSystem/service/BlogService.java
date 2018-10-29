package com.springboot.blog.BlogSystem.service;

import com.springboot.blog.BlogSystem.domain.Blog;
import com.springboot.blog.BlogSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface BlogService {

    //save blog
    Blog saveBlog(Blog blog);

    //delete blog
    void removeBlog(Long id);

    //get blot
    Optional<Blog> getBlogById(Long id);

    // search based on blog title(newest)
    Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable);

    // search based on blog title(popular)
    Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable);

    //based on reading number
    void readingIncrease(Long id);

    Blog createComment(Long blogId, String commentContent);

    void removeComment(Long blogId, Long CommentId);




}
