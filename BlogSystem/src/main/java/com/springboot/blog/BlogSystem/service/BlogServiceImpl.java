package com.springboot.blog.BlogSystem.service;

import com.springboot.blog.BlogSystem.domain.Blog;
import com.springboot.blog.BlogSystem.domain.Comment;
import com.springboot.blog.BlogSystem.domain.User;
import com.springboot.blog.BlogSystem.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        Blog returnBlog = blogRepository.save(blog);
        return returnBlog;
    }

    @Transactional
    @Override
    public void removeBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    @Override
    public Page<Blog> listBlogsByTitleVote(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        String tags = title;
        Page<Blog> blogs = blogRepository.findByTitleLikeAndUserOrTagsLikeAndUserOrderByCreateTimeDesc(title,
                user, tags, user, pageable);

        return blogs;
    }

    @Override
    public Page<Blog> listBlogsByTitleVoteAndSort(User user, String title, Pageable pageable) {
        title = "%" + title + "%";
        Page<Blog> blogs = blogRepository.findByUserAndTitleLike(user, title, pageable);
        return blogs;
    }

    @Override
    public void readingIncrease(Long id) {
        Blog blog = blogRepository.findById(id).get();
        blog.setReadSize(blog.getCommentSize() + 1);
        this.saveBlog(blog);
    }

    @Override
    public Blog createComment(Long blogId, String commentContent) {
        Blog originalBlog = blogRepository.findById(blogId).get();
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Comment comment = new Comment(user, commentContent);
        originalBlog.addComment(comment);
        return this.saveBlog(originalBlog);
    }

    @Override
    public void removeComment(Long blogId, Long commentId) {
        Blog originalBlog = blogRepository.findById(blogId).get();
        originalBlog.removeComment(commentId);
        this.saveBlog(originalBlog);
    }
}
