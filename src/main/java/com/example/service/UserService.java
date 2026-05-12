package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {

        userRepository.save(user);

    }

    public User authenticateUser(String email,
            String password) {

        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent()) {

            User existingUser = user.get();

            if (existingUser.getPassword()
                    .equals(password)) {

                return existingUser;
            }
        }

        return null;
    }
}