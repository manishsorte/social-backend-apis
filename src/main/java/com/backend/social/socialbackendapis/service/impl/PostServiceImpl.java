package com.backend.social.socialbackendapis.service.impl;

import com.backend.social.socialbackendapis.entity.Category;
import com.backend.social.socialbackendapis.entity.Post;
import com.backend.social.socialbackendapis.entity.User;
import com.backend.social.socialbackendapis.exception.ResourceNotFoundException;
import com.backend.social.socialbackendapis.payload.PostDto;
import com.backend.social.socialbackendapis.payload.PostPaginationResponse;
import com.backend.social.socialbackendapis.repository.CategoryRepository;
import com.backend.social.socialbackendapis.repository.PostRepository;
import com.backend.social.socialbackendapis.repository.UserRepository;
import com.backend.social.socialbackendapis.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

        User retrievedUserById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));

        Category retrievedCategoryById = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));

        Post post = this.dtoToPost(postDto);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setCategory(retrievedCategoryById);
        post.setUser(retrievedUserById);
        Post createdPost = this.postRepository.save(post);
        return this.postToDto(createdPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post retrievedPost = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        retrievedPost.setTitle(postDto.getTitle());
        retrievedPost.setImageName(postDto.getImageName());
        retrievedPost.setContent(postDto.getContent());
        Post updatedPost = this.postRepository.save(retrievedPost);
        return this.postToDto(updatedPost);

    }

    @Override
    public void deletePost(Integer postId) {

        this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        this.postRepository.deleteById(postId);
    }

    @Override
    public PostDto getPostById(Integer postId) {

        Post retrievedPost = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        return this.postToDto(retrievedPost);
    }

    @Override
    public PostPaginationResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageInfo = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePosts = this.postRepository.findAll(pageInfo);
        List<Post> listOfAllPosts = pagePosts.getContent();
        List<PostDto> postDtoList = listOfAllPosts.stream()
                .map(this::postToDto)
                .collect(Collectors.toList());

        PostPaginationResponse postResponse = new PostPaginationResponse();
        postResponse.setContent(postDtoList);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

        return postResponse;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {

        Category retrievedCategory = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", categoryId));
        List<Post> listOfPostByCategory = this.postRepository.findByCategory(retrievedCategory);
        List<PostDto> posts = listOfPostByCategory.stream()
                .map(this::postToDto)
                .collect(Collectors.toList());
        return posts;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {

        User retrievedUserById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        List<Post> listOfPostByUser = this.postRepository.findByUser(retrievedUserById);
        List<PostDto> posts = listOfPostByUser.stream()
                .map(this::postToDto)
                .collect(Collectors.toList());
        return posts;
    }

    private Post dtoToPost(PostDto postDto) {
        return this.modelMapper.map(postDto, Post.class);
    }

    private PostDto postToDto(Post post) {
        return this.modelMapper.map(post, PostDto.class);
    }

}
