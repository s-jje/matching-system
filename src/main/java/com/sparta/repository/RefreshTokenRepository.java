package com.sparta.repository;

import com.sparta.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
