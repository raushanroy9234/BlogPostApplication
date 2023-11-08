package com.mountblue.blogApplication.BlogApplication.controller;
import com.mountblue.blogApplication.BlogApplication.model.Comment;
import com.mountblue.blogApplication.BlogApplication.model.Post;
import com.mountblue.blogApplication.BlogApplication.service.CommentService;
import com.mountblue.blogApplication.BlogApplication.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
@Controller
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;
    private PostService postService;
    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }
    @GetMapping("/form")
    public String showCommentForm(@RequestParam("postId") int postId, Model model) {
        Comment comment = new Comment();
        comment.setPost(postService.getPostById(postId));
        model.addAttribute("comment", comment);
        return "comment-form";
    }
    @PostMapping("/submit")
    public String submitComment(@RequestParam("postId") int postId,
                                @RequestParam("comment") String commentText,
                                @RequestParam("name") String name,
                                @RequestParam("email") String email) {
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setName(name);
        comment.setEmail(email);
        comment.setComment(commentText);
        Date currentDate = new Date();
        comment.setCreated_at(currentDate);
        comment.setUpdated_at(currentDate);
        comment.setPost(post);
        commentService.saveComment(comment);
        return "redirect:/posts/list";
    }
}
