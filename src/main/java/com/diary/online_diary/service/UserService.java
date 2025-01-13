package com.diary.online_diary.service;

import com.diary.online_diary.model.User;
import com.diary.online_diary.repository.UserRepository;
import com.diary.online_diary.util.JwtTokenUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Lazy private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public void registerUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("user");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public Optional<String> loginUser(String email, String rawPassword) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return Optional.of(jwtTokenUtil.generateToken(user.getEmail(), user.getRole()));
            }
        }
        return Optional.empty();
    }


    public void processOAuth2User(String email, String name) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        if (existingUser.isEmpty()) {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setNickname(name);
            newUser.setRole("user");

            String generatedPassword = generateRandomPassword(12);
            String encodedPassword = passwordEncoder.encode(generatedPassword);
            newUser.setPassword(encodedPassword);

            userRepository.save(newUser);
        }
    }

    private String generateRandomPassword(int length) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }
        return password.toString();
    }
}
