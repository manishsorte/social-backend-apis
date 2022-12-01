package com.backend.social.socialbackendapis.service.impl;

import com.backend.social.socialbackendapis.entity.Comment;
import com.backend.social.socialbackendapis.entity.Post;
import com.backend.social.socialbackendapis.entity.User;
import com.backend.social.socialbackendapis.exception.ResourceNotFoundException;
import com.backend.social.socialbackendapis.payload.CommentDto;
import com.backend.social.socialbackendapis.repository.CommentRepository;
import com.backend.social.socialbackendapis.repository.PostRepository;
import com.backend.social.socialbackendapis.repository.UserRepository;
import com.backend.social.socialbackendapis.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId) {
        Post retrievedPost = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        User retrievedUserById = this.userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "ID", userId));
        Comment comment = this.dtoToComment(commentDto);
        comment.setPost(retrievedPost);
        comment.setUser(retrievedUserById);
        Comment createdComment = this.commentRepository.save(comment);
        return this.commentToDto(createdComment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment retrievedComment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));

        retrievedComment.setContent(commentDto.getContent());
        Comment updatedComment = this.commentRepository.save(retrievedComment);
        return this.commentToDto(updatedComment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        Comment retrievedComment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        return this.commentToDto(retrievedComment);
    }

    @Override
    public void deleteCommentById(Integer commentId) {
        Comment retrievedComment = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        this.commentRepository.deleteById(retrievedComment.getId());
    }

    private Comment dtoToComment(CommentDto commentDto) {
        return mapper.map(commentDto, Comment.class);
    }

    private CommentDto commentToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
