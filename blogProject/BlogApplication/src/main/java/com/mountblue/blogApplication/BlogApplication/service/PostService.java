package com.mountblue.blogApplication.BlogApplication.service;

import com.mountblue.blogApplication.BlogApplication.model.Post;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    List<Post> getAllPosts();
    Post getPostById(int id);
//    Post getPostById(int theId);



    void savePost(Post post);


    void updatePost(Post post);

    void deletePost(int id);

    List<Post> searchPosts(String theTitle);

    List<Post> getAllPostsSortedByTitleAscending();

    List<Post> getAllPostsSortedByTitleDescending();
}

//    Employee findById(int theId);