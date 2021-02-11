package com.watersolution.inventory.core.config.security.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.user.model.db.User;
import com.watersolution.inventory.core.config.security.model.Token;
import com.watersolution.inventory.core.config.security.repository.TokenRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public void saveToken(User user) {
        Token token = new Token();
        token.setUserName(user.getUserName());
        token.setToken(passwordEncoder.encode(user.getToken()));
        token.setStatus(Status.ACTIVE.getValue());
        token.fillCompulsory(user.getUserName());
        tokenRepository.save(token);
    }

    @SneakyThrows
    @Override
    public void deleteToken(User user) {
//        tokenRepository.findByUserNameAndStatus(user.getUserName(), Status.ACTIVE.getValue()).ifPresent(tokens -> {
//            tokenRepository.deleteAll(tokens);
//        });
    }
}
