package com.mountblue.blogApplication.BlogApplication.Repository;
import com.mountblue.blogApplication.BlogApplication.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    List<Post> findByTitleContainingIgnoreCase(String searchTerm);

    List<Post> findAllByOrderByTitleAsc();

    List<Post> findAllByOrderByTitleDesc();
//    List<Post> findAllByOrderByCreatedAtAsc();
//
//    List<Post> findAllByOrderByCreatedAtDesc();
}
