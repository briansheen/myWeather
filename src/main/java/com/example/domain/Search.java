package com.example.domain;

/**
 * Created by bsheen on 8/16/17.
 */
public class Search {

    private Integer searchId;
    private User user;
    private String searchQuery;

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

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
