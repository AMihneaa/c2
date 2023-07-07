package com.mihnea.springboot3.c2.Video;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
public class VideoEntity {

    private @Id @GeneratedValue Long id;
    private String userName;
    private String name;
    private String description;


    protected VideoEntity() {
        this(null, null, null);
    }

    VideoEntity(String userName, String name, String description) {
        this.id = null;
        this.userName = userName;
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

}
