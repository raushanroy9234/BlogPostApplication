package com.mountblue.blogApplication.BlogApplication.service;

import com.mountblue.blogApplication.BlogApplication.Repository.PostRepository;
import com.mountblue.blogApplication.BlogApplication.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Service
public class PostServiceImp implements PostService{

    private PostRepository postRepository;
    private PostService postService;

    public PostServiceImp(PostRepository thePostRepository){
        postRepository = thePostRepository;
    }


//    @Override
//    public List<Post> getAllPosts(Sort sort) {
//        return postRepository.findAll(sort);
//    }
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void updatePost(Post post) {
        // Validate the post or perform any necessary checks

        // Retrieve the existing post from the database
        Post existingPost = postService.getPostById(post.getId());

        // Check if the post exists
        if (existingPost == null) {
            throw new IllegalArgumentException("Post not found with ID: " + post.getId());
        }

        // Update the necessary fields of the existing post
        existingPost.setTitle(post.getTitle());
        existingPost.setExcerpt(post.getExcerpt());
        existingPost.setContent(post.getContent());
        existingPost.setUpdatedAt(LocalDate.now());

        // Save the updated post in the database
        postService.updatePost(existingPost);
    }

    @Override
    public Post getPostById(int theId){
        Optional<Post> result = postRepository.findById(theId);
        Post thePost = null;
        if(result.isPresent()){
            thePost = result.get();
        }
        else{
            throw new RuntimeException("Did not find the post "+ theId);
        }
        return thePost;
    }



    @Override
    public void savePost(Post post) {
        post.setCreatedAt(LocalDate.now());
        post.setUpdatedAt(LocalDate.now());
        post.setPublishedAt(LocalDate.now());
        postRepository.save(post);
    }



    @Override
    public void deletePost(int id) {
        postRepository.deleteById(id);
    }

    public List<Post> searchPosts(String searchTerm) {
        return postRepository.findByTitleContainingIgnoreCase(searchTerm);
    }



    @Override
    public List<Post> getAllPostsSortedByTitleAscending() {
        return postRepository.findAllByOrderByTitleAsc();
    }

    @Override
    public List<Post> getAllPostsSortedByTitleDescending() {
        return postRepository.findAllByOrderByTitleDesc();
    }


}
