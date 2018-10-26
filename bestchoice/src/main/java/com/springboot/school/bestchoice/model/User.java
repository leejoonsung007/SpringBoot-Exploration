package com.springboot.school.bestchoice.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class User {

    private Integer id;

    private String username;

    private String email;

    private Date member_since;

    private Date last_seen;

    private String location;

    private Integer role_id;

    private String password;

    private String confirmPassword;

    private Integer confirmed;

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    private Integer facebook_id;

    private String photo;

    private String login_type;

    private MultipartFile avatarFile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getMember_since() {
        return member_since;
    }

    public void setMember_since(Date member_since) {
        this.member_since = member_since;
    }

    public Date getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(Date last_seen) {
        this.last_seen = last_seen;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getFacebook_id() {
        return facebook_id;
    }

    public void setFacebook_id(Integer facebook_id) {
        this.facebook_id = facebook_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }
}
