package com.tripzy.Repository;

import com.tripzy.Entities.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken,Long> {
    Optional<PasswordResetToken> findByUserId(Long userId);
    Optional<PasswordResetToken> findByToken(String token);
}
