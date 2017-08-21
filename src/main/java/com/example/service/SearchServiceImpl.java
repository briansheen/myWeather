package com.example.service;

import com.example.domain.Search;
import com.example.domain.User;
import com.example.repository.SearchRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.ArrayUtils;

import java.util.*;

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
        int listSize = user.getSearches().size();
        List<Search> lastTen = new ArrayList<Search>();
        if (user.getSearches() != null) {
            if(listSize>=10){
                lastTen = user.getSearches().subList(listSize-10, listSize);
                Collections.reverse(lastTen);
                return lastTen;
            } else {
                lastTen = user.getSearches().subList(0,listSize);
                Collections.reverse(lastTen);
                return lastTen;
            }
        }
        return null;
    }

    @Override
    public Search addSearch(Search search) {
        return searchRepository.save(search);
    }
}
