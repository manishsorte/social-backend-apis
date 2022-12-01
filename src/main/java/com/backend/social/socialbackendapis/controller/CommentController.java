package com.backend.social.socialbackendapis.controller;

import com.backend.social.socialbackendapis.payload.CommentDto;
import com.backend.social.socialbackendapis.service.impl.CommentServiceImpl;
import com.backend.social.socialbackendapis.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    @PostMapping("/create/{postId}/user/{userId}")
    ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                             @PathVariable Integer postId,
                                             @PathVariable Integer userId) {
        CommentDto createdComment = this.commentService.createComment(commentDto, postId, userId);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,
                                             @PathVariable Integer commentId) {
        CommentDto updatedComment = this.commentService.updateComment(commentDto, commentId);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable Integer commentId) {
        CommentDto retrievedComment = this.commentService.getCommentById(commentId);
        return new ResponseEntity<>(retrievedComment, HttpStatus.FOUND);
    }

    @DeleteMapping("/{commentId}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        this.commentService.deleteCommentById(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment deleted successfully", true), HttpStatus.FOUND);
    }

}
