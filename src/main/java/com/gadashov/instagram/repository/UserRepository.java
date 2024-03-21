package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/10/2024
 * Time: 12:40 PM
 */


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndEmail(String username, String email);

    Optional<User> findByUsername(String username);

}
