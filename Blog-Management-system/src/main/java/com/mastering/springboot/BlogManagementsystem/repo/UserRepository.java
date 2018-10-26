package com.mastering.springboot.BlogManagementsystem.repo;

import com.mastering.springboot.BlogManagementsystem.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    User findByUsername(String username);
}
