package com.watersolution.inventory.core.config.security.repository;

import com.watersolution.inventory.core.config.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<List<Token>> findByUserNameAndStatus(String token, int status);
}
