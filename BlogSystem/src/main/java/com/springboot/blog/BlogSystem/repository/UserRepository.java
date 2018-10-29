package com.springboot.blog.BlogSystem.repository;

import com.springboot.blog.BlogSystem.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // paging query
    Page<User> findByNameLike(String name, Pageable pageable);

    User findByUsername(String username);

}

