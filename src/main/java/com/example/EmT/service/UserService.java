package com.example.EmT.service;

import com.example.EmT.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findById(String userId);
}
