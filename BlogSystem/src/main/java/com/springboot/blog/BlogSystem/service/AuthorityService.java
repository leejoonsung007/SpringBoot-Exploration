package com.springboot.blog.BlogSystem.service;

import com.springboot.blog.BlogSystem.domain.Authority;

import java.util.Optional;

public interface AuthorityService {
    Optional<Authority> getAuthorityById(Long id);
}
