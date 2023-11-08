package com.mountblue.blogApplication.BlogApplication.controller;

import com.mountblue.blogApplication.BlogApplication.model.Comment;
import com.mountblue.blogApplication.BlogApplication.model.Post;
import com.mountblue.blogApplication.BlogApplication.service.CommentService;
import com.mountblue.blogApplication.BlogApplication.service.PostService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private CommentService commentService;

    public PostController(PostService postService,CommentService commentService) {

        this.postService = postService;
        this.commentService = commentService;
    }

    // Other methods...

    @GetMapping("/list")
    public String listPosts(Model theModel){

        List<Post> thePosts = postService.getAllPosts();
        theModel.addAttribute("posts",thePosts);
        return "list-blogs";
    }
    @GetMapping("/add")
    public String addPostForm(Model model) {

        model.addAttribute("post", new Post());
        return "add-post";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") Post thePost) {
        postService.savePost(thePost);
        return "redirect:/posts/add";
    }

    @PutMapping("/showPostForUpdate")
    public String showFormForUpdate(@RequestParam("postId") int postId, @ModelAttribute("post") Post updatedPost) {
        try {
            Post existingPost = postService.getPostById(postId);

            if (existingPost != null) {
                existingPost.setTitle(updatedPost.getTitle());
                existingPost.setExcerpt(updatedPost.getExcerpt());
                existingPost.setContent(updatedPost.getContent());

                postService.updatePost(existingPost);

                return "redirect:/update-post/" + existingPost.getId(); // Redirect to the updated post details page
            } else {
                throw new RuntimeException("Post not found for ID: " + postId);
            }
        } catch (Exception e) {
            // Handle the exception appropriately (e.g., show error message to the user)
            // Redirect to an error page or display an error message in the view
            return "error";
        }
    }


    @GetMapping("/delete")
    public String delete(@RequestParam("postId") int theId){
        postService.deletePost(theId);

        return "redirect:/posts/list";
    }
    @GetMapping("/post/{postId}")
    public String showPost(@PathVariable("postId") int id, Model model) {
        // Logic to fetch the post with the given postId from your data source
        Post post = postService.getPostById(id);
        List<Comment> comments  =  commentService.getAllComments(id);

        // Add the post to the model
        model.addAttribute("commentsList",comments);
        model.addAttribute("posts", post);

        // Return the view name for the post details
        return "post-details";
    }

    @GetMapping("/search")
    public String Search(@RequestParam("postTitle") String title, Model model){

        List<Post> theposts = postService.searchPosts(title);
        model.addAttribute("posts",theposts);
        return "list-blogs";
    }


    @GetMapping("/sorting")
    public String getAllPostsSortedByTitle(@RequestParam(value = "sort") String sortOption, Model model) {
        List<Post> thePosts;
        if (sortOption.equals("asc")) {
            thePosts = postService.getAllPostsSortedByTitleAscending();
            model.addAttribute("posts", thePosts);
        }
        else if(sortOption.equals("desc")) {
            thePosts = postService.getAllPostsSortedByTitleDescending();
            model.addAttribute("posts", thePosts);
        }

        return "list-blogs";
    }


}

