package com.backend.social.socialbackendapis.repository;

import com.backend.social.socialbackendapis.entity.Category;
import com.backend.social.socialbackendapis.entity.Post;
import com.backend.social.socialbackendapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByCategory(Category categoryID);
    List<Post> findByUser(User userId);
}
