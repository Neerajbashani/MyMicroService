package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PageDTO;
import com.springboot.blog.payload.PostDTO;

import com.springboot.blog.repository.PostJPARepository;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostJPARepository repo;

    public PostServiceImpl(PostJPARepository repo) {
        this.repo = repo;
    }

    @Override
    public PostDTO createPost(PostDTO postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post postData = repo.save(post);
        PostDTO DTOResponse = new PostDTO();
        DTOResponse.setId(postData.getId());

        DTOResponse.setTitle(postData.getTitle());

        DTOResponse.setContent(postData.getContent());

        DTOResponse.setDescription(postData.getDescription());
        return DTOResponse;
    }

    @Override
    public PageDTO getPosts(int pageNo, int pageSize) {
        List<PostDTO> list1 = new ArrayList<>();

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("title").descending());
        Page<Post> list = repo.findAll(paging);

        List<Post> listPfPost = list.getContent();
        List<PostDTO> content = listPfPost.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PageDTO reponse = new PageDTO();
        reponse.setContent(content);
        reponse.setPageNo(list.getNumber());
        reponse.setPageSize(list.getSize());
        reponse.setTotalPages(list.getTotalPages());
        reponse.setTotalElement(list.getTotalElements());
        reponse.setLast(list.isLast());
        return reponse;

    }

    private PostDTO mapToDTO(Post post) {
        PostDTO dtoPost = new PostDTO();
        dtoPost.setId(post.getId());
        dtoPost.setDescription(post.getDescription());
        dtoPost.setContent(post.getContent());

        dtoPost.setTitle(post.getTitle());
        return dtoPost;
    }

    @Override
    public PostDTO getPost(Long id) {
        Post post = repo.findById(id).get();
        PostDTO dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        return dto;

    }

    @Override
    public void deletePost(Long id) {
        repo.deleteById(id);
    }

    public PostDTO updatePost(PostDTO postDto, Long id) {
        Post dto = new Post();
        dto = repo.findById(id).get();
        dto.setId(id);
        dto.setDescription(postDto.getDescription());
        dto.setTitle(postDto.getTitle());
        dto.setContent(postDto.getContent());
        dto = repo.save(dto);

        return mapToDTO(dto);
    }
}
