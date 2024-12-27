package com.diary.online_diary.service;

import com.diary.online_diary.model.User;
import com.diary.online_diary.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
        userRepository.save(user);
    }
}
