package com.mastering.springboot.BlogManagementsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "blog_user", type = "user")
@Data
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private String role;

    private String description;

    public User() {
    }

    public User(String id, String username, String password, String role, String description) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getDescription() {
        return description;
    }
}
