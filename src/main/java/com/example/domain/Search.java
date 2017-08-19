package com.example.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by bsheen on 8/16/17.
 */

@Entity
@Table(name="search")
public class Search {

    private Integer searchId;
    private User user;

    @NotNull
    private String searchQuery;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    @ManyToOne()
    @JoinColumn(name="username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Search search = (Search) o;

        return searchId != null ? searchId.equals(search.searchId) : search.searchId == null;
    }

    @Override
    public int hashCode() {
        return searchId != null ? searchId.hashCode() : 0;
    }
}
