package com.example.demo.service;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public Post addPosts(Post post) {
        return postRepository.save(post);
    }

    public Post editPost(Integer postId, Post post) {
        Post editedPost = getPostById(postId);
        if (editedPost != null) {
            editedPost.setTitle(post.getTitle());
            editedPost.setBody(post.getBody());
            postRepository.save(editedPost);
            return editedPost;
        }
        return null;
    }

    private Post getPostById(Integer postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        return optionalPost.orElse(null);
    }

    public boolean deletePost(Integer postId) {
        Post deletedPost = getPostById(postId);
        if (deletedPost == null)
            return false;
        else try {
            postRepository.delete(deletedPost);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
