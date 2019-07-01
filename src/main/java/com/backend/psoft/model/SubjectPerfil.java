package com.backend.psoft.model;

import java.util.ArrayList;
import java.util.List;

public class SubjectPerfil {

    private String name;
    private long id;
    private List<Like> likes = new ArrayList<Like>();
    private List<Comment> comments = new ArrayList<Comment>();
    private Integer alvalUser;

    public SubjectPerfil() {

    }
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Integer getAlvalUser() {
        return alvalUser;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setAlvalUser(Integer alvalUser) {
        this.alvalUser = alvalUser;
    }
}
