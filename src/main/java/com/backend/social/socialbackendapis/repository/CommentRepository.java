package com.backend.social.socialbackendapis.repository;

import com.backend.social.socialbackendapis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
