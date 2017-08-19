package com.example.service;

import com.example.domain.Search;

import java.util.List;

/**
 * Created by bsheen on 8/19/17.
 */
public interface SearchService {
    List<Search> findSearchesByUser(String username);
    Search addSearch(Search search);
}
