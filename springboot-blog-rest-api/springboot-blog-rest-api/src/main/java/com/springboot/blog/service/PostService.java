package com.springboot.blog.service;

import com.springboot.blog.payload.PageDTO;
import com.springboot.blog.payload.PostDTO;
import org.springframework.stereotype.Service;

@Service
public interface PostService {

    PostDTO createPost(PostDTO post);
    PageDTO getPosts(int pageNo, int pageSize);
    PostDTO getPost(Long id);
    void deletePost(Long id);
    public PostDTO updatePost(PostDTO postDto, Long id);

}
