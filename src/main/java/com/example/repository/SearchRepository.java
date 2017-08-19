package com.example.repository;

import com.example.domain.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bsheen on 8/19/17.
 */

@Repository
public interface SearchRepository extends JpaRepository<Search, Integer> {
}
