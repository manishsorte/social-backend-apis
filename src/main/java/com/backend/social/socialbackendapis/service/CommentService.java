package com.backend.social.socialbackendapis.service;

import com.backend.social.socialbackendapis.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);

    CommentDto updateComment(CommentDto commentDto, Integer commentId);

    CommentDto getCommentById(Integer commentId);

    void deleteCommentById(Integer commentId);

}
