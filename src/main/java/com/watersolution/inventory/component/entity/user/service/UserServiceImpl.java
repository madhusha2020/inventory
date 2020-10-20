package com.watersolution.inventory.component.entity.user.service;

import com.watersolution.inventory.component.common.util.Status;
import com.watersolution.inventory.component.entity.user.model.User;
import com.watersolution.inventory.component.entity.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(User user) {
        user.setStatus(Status.ACTIVE.getValue());
        user.fillCompulsory(user.getUserId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public void updateFailedAttempts(User user) {
        Optional<User> updatableUser = userRepository.findByUserNameAndStatus(user.getUserName(), Status.ACTIVE.getValue());
        if(updatableUser.isPresent()){
            updatableUser.get().setFailedAttempts(updatableUser.get().getFailedAttempts() + 1);
            userRepository.save(updatableUser.get());
        }
    }
}