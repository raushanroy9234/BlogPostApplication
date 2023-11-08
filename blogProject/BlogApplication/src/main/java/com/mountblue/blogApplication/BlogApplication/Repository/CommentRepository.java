package com.mountblue.blogApplication.BlogApplication.Repository;

import com.mountblue.blogApplication.BlogApplication.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByPostId(int postId);
}
