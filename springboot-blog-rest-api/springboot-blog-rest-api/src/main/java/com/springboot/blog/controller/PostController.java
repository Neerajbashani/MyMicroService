package com.springboot.blog.controller;


import com.springboot.blog.payload.PageDTO;
import com.springboot.blog.payload.PostDTO;

import com.springboot.blog.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@EnableJpaRepositories
public class PostController {

    @Autowired
    private PostServiceImpl servc;

    public PostController(PostServiceImpl servc) {
        this.servc = servc;
    }

    @PostMapping("/post")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post) {
//        PostDTO dtpost = servc.createPost(post);
        return new ResponseEntity<>(servc.createPost(post), HttpStatus.CREATED);


    }


    @GetMapping("/post/")
    public ResponseEntity<PageDTO> getPosts(@RequestParam(defaultValue = "0") Integer pageNo,
                                            @RequestParam(defaultValue = "1") Integer pageSize) {
//        PostDTO dtpost = servc.createPost(post);
        return new ResponseEntity<>(servc.getPosts(pageNo, pageSize), HttpStatus.OK);


    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("id") Long id) {
        if (servc.getPost(id) == null) {
            throw new ResourceNotFoundException("Not found", "ID", "value", "");
        } else {
            return ResponseEntity.ok(servc.getPost(id));
        }
//        return new ResponseEntity<>(servc.getPost(id), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        if (servc.getPost(id) == null) {
            throw new ResourceNotFoundException("Not found", "ID", "value", "");
        } else {
            servc.deletePost(id);

        }
        return new ResponseEntity<>("Post deleted succesful", HttpStatus.OK);
    }


    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDto, @PathVariable("id") Long id) {
        if (servc.getPost(id) == null) {
            throw new ResourceNotFoundException("Not found", "ID", "value", "");
        } else {


            return new ResponseEntity<>(servc.updatePost(postDto, id), HttpStatus.OK);
        }
//        return new ResponseEntity<>(servc.getPost(id), HttpStatus.OK);
    }

}
