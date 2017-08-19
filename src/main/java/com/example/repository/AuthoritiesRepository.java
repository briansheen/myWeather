package com.example.repository;

import com.example.domain.AuthCompKey;
import com.example.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bsheen on 8/19/17.
 */
public interface AuthoritiesRepository extends JpaRepository<Authorities, AuthCompKey> {
}
