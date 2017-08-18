package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bsheen on 8/17/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOne(username);
    }

    @Override
    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEnabled(true);
        LocalDateTime ldt = LocalDateTime.now();
        user.setCreatedAt(ldt);
        user = userRepository.save(user);
        return user;
    }
}
