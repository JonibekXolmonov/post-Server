package com.example.demo.controller;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public HttpEntity<?> getPosts() {
        List<Post> postList = postService.getPosts();
        return ResponseEntity.ok(postList);
    }

    @PostMapping("/posts")
    public HttpEntity<?> addPost(@RequestBody Post post) {
        Post savedPost = postService.addPosts(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPost);
    }

    @PutMapping("/posts/{id}")
    public HttpEntity<?> editPost(@PathVariable(value = "id") Integer postId, @RequestBody Post post) {
        Post editedPost = postService.editPost(postId, post);
        return ResponseEntity.status(editedPost != null ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(editedPost);
    }

    @DeleteMapping("/posts/{id}")
    public HttpEntity<?> deletePost(@PathVariable(value = "id") Integer postId) {
        boolean deleted = postService.deletePost(postId);
        return ResponseEntity.status(deleted ? HttpStatus.NO_CONTENT : HttpStatus.CONFLICT).body(deleted);
    }
}
