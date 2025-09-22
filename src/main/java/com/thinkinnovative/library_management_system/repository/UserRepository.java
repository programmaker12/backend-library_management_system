package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByMemberMemberID(int id);
}
