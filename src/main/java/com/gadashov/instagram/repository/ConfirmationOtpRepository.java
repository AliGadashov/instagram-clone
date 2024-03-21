package com.gadashov.instagram.repository;

import com.gadashov.instagram.model.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Author: Ali Gadashov
 * Version: v1.0
 * Date: 3/13/2024
 * Time: 1:44 PM
 */

public interface ConfirmationOtpRepository extends JpaRepository<Otp,Long> {
    @Query(value =
            "select o from Otp o " +
                    "join User u on o.user = u " +
                    "where o.otp =:otp " +
                    "and o.expiredTime > current_timestamp "
    )
    Optional<Otp> findByOtp (Integer otp);
}
