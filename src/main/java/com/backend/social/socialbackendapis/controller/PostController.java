package com.backend.social.socialbackendapis.controller;

import com.backend.social.socialbackendapis.payload.PostDto;
import com.backend.social.socialbackendapis.payload.PostPaginationResponse;
import com.backend.social.socialbackendapis.service.impl.PostServiceImpl;
import com.backend.social.socialbackendapis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @PostMapping("/user/{userID}/category/{categoryId}")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto
            , @PathVariable Integer userID
            , @PathVariable Integer categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userID, categoryId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,
                                              @PathVariable Integer postId) {
        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<PostDto>> getAllPostByCategory(@PathVariable Integer categoryId) {
        List<PostDto> listOfAllPosts = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(listOfAllPosts, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getAllPostByUser(@PathVariable Integer userId) {
        List<PostDto> listOfAllPosts = this.postService.getPostByUser(userId);
        return new ResponseEntity<>(listOfAllPosts, HttpStatus.OK);
    }

    @GetMapping("/allPost")
    public ResponseEntity<PostPaginationResponse> getAllPost(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                             @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                             @RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
                                                             @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDirection) {
        PostPaginationResponse allPost = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto post = this.postService.getPostById(postId);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<ApiResponse> deletePostById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);

    }

}
