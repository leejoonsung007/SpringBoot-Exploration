package com.mastering.springboot.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import lombok.Data;

import java.sql.Timestamp;

@Entity // mark the comment class as a JPA entity
@Table(name = "rb_comment") // mention the table name to which the comment class need to be mapped the
@EntityListeners(AuditingEntityListener.class)
@Data // mark a POJO as a class that will hold data
public class Comment {

    @Id
    @GeneratedValue //mark it as an aoto-generated value
    private Long id;

    private String comment;

    @Enumerated(EnumType.STRING) //notify the value of enum Commentype needs to be persisted as a string type in the data bs=ase
    private CommentType type;

    @CreatedDate
    private Timestamp createdDate;

    @CreatedBy
    private String createdBy;

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public CommentType getType() {
        return type;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }


}
