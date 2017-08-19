package com.example.service;

import com.example.domain.Search;
import com.example.domain.User;
import com.example.repository.SearchRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by bsheen on 8/19/17.
 */

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Search> findSearchesByUser(String username) {
        User user = userRepository.findOne(username);
        if (user.getSearches() != null) {
            return user.getSearches();
        }
        return null;
    }

    @Override
    public Search addSearch(Search search) {
        return searchRepository.save(search);
    }
}
