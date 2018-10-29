package com.springboot.blog.BlogSystem.repository;

import com.springboot.blog.BlogSystem.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
