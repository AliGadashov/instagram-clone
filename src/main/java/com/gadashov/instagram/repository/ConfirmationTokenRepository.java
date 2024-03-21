package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.ConfirmationToken;
import com.gadashov.instagram.model.enums.ConfirmationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:45 PM
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findByTokenAndType(String token, ConfirmationType type);
}
