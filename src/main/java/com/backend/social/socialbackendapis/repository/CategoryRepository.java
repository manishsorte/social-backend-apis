package com.backend.social.socialbackendapis.repository;

import com.backend.social.socialbackendapis.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
