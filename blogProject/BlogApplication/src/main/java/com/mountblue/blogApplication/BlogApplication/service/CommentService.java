package com.mountblue.blogApplication.BlogApplication.service;

import com.mountblue.blogApplication.BlogApplication.Repository.CommentRepository;
import com.mountblue.blogApplication.BlogApplication.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(int id) {
        return commentRepository.findAllByPostId(id);
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }


    public void deleteComment(int id) {
        commentRepository.deleteById(id);
    }
}
