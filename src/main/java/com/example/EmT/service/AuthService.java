package com.example.EmT.service;

import com.example.EmT.model.User;

public interface AuthService {
    User getCurrentUser();
    String getCurrentUserId();
}
