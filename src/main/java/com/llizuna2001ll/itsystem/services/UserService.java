package com.llizuna2001ll.itsystem.services;


import com.llizuna2001ll.itsystem.entities.User;
import com.llizuna2001ll.itsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User Not Found"));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long userId, User user) {
        if (userRepository.existsById(userId)) {
            user.setId(userId);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
}
