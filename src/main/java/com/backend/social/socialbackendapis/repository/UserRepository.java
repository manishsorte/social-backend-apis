package com.backend.social.socialbackendapis.repository;

import com.backend.social.socialbackendapis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
