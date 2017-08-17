package com.example.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by bsheen on 8/16/17.
 */

public class User {

    private String username;
    private String password;
    private List<Search> searches;
    private Boolean enabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



}
