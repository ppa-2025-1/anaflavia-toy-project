package com.example.demo.dto;

import com.example.demo.model.entity.Profile;

public class UserResponse {
    private Integer id;
    private Profile profile;
    private String email;

    public UserResponse() {}

    public UserResponse(Integer id, Profile profile, String email) {
        this.id = id;
        this.email = email;
        this.profile = profile;
    }

    // Getters e setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
