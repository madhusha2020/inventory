package com.watersolution.inventory.core.config.security.repository;

import com.watersolution.inventory.core.config.security.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByUserNameAndStatus(String token, int status);
}
