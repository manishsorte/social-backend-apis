package com.backend.social.socialbackendapis.service;


import com.backend.social.socialbackendapis.payload.PostDto;
import com.backend.social.socialbackendapis.payload.PostPaginationResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto post, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto post, Integer post_id);

    void deletePost(Integer post_id);

    PostDto getPostById(Integer postId);

    PostPaginationResponse getAllPosts(Integer pageNumber, Integer pageSize);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

}
