package com.mastering.springboot.service;

import com.mastering.springboot.model.Comment;
import com.mastering.springboot.repo.CommentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional(readOnly = true)
public class CommentService {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Comment> saveAll(List<Comment> comments ){
        LOGGER.info("saving {}", comments);
        return commentRepository.saveAll(comments);
    }

    public List<Comment> getALLCommentForToday(){
        LocalDate localDate = LocalDate.now();
        return commentRepository.findByCreatedYearAndMonthAndDay(localDate.getYear(),
                localDate.getMonth().getValue(), localDate.getDayOfMonth());
    }




}
