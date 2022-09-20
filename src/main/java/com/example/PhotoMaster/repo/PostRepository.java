package com.example.PhotoMaster.repo;

import com.example.PhotoMaster.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository <Post, Long> {
    List<Post> findByNameContains (String name);
    Post findByName (String name);

}
