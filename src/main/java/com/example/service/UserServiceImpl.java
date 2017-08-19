package com.example.service;

import com.example.domain.AuthCompKey;
import com.example.domain.Authorities;
import com.example.domain.Search;
import com.example.domain.User;
import com.example.repository.AuthoritiesRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bsheen on 8/17/17.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthoritiesRepository authoritiesRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = userRepository.findOne(username);
        if(user != null){
            if(user.getSearches()!=null){
                user.getSearches().size();
            }
        }
        return user;
    }

    @Override
    @Transactional
    public User addUser(String username) {
        User user = new User();
        user.setUsername(username);
        user.setEnabled(true);
        LocalDateTime ldt = LocalDateTime.now();
        user.setCreatedAt(ldt);
        user = userRepository.save(user);

        AuthCompKey authCompKey = new AuthCompKey();
        authCompKey.setUsername(username);
        authCompKey.setAuthority("ROLE_USER");
        Authorities authorities = new Authorities();
        authorities.setCompKey(authCompKey);
        authoritiesRepository.save(authorities);

        return user;
    }

}
