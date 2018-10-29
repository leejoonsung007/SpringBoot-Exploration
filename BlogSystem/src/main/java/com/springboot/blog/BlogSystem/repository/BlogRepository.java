package com.springboot.blog.BlogSystem.repository;

import com.springboot.blog.BlogSystem.domain.Blog;
import com.springboot.blog.BlogSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BlogRepository extends JpaRepository<Blog, Long> {
    // search based on username, blog title and page
    Page<Blog> findByUserAndTitleLike(User user, String title, Pageable pageable);

    // based on time
    Page<Blog> findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(String title, User user, String tags, User user2, Pageable pageable);
}
