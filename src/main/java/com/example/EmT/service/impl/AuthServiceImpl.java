package com.example.EmT.service.impl;

import com.example.EmT.model.User;
import com.example.EmT.repository.UserRepository;
import com.example.EmT.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        return this.userRepository.findById("dummy").orElseGet(()->{
            User user = new User();
            user.setUsername("dummy");
            return this.userRepository.save(user);
        });
    }

    @Override
    public String getCurrentUserId() {
        return this.getCurrentUser().getUsername();
    }
}
