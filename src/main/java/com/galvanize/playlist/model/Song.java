package com.galvanize.playlist.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode
@ToString
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;

    public Song(){}


    public Song(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
