package com.backend.psoft.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectProfile {

    private String name;
    private long id;
    private Integer likes;
    private Integer unlikes;
    private List<Comment> comments = new ArrayList<Comment>();
    private Integer avalUser;

    public SubjectProfile() {

    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getLikes() {
        return likes;
    }

    public Integer getUnlikes() {
        return unlikes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Integer getAvalUser() {
        return avalUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public void setUnlikes(Integer unlikes) {
        this.unlikes = unlikes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setAvalUser(Integer avalUser) {
        this.avalUser = avalUser;
    }
}
