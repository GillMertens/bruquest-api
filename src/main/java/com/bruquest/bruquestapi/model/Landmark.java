package com.bruquest.bruquestapi.model;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "LANDMARK")
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DIFFICULTY")
    private int difficulty;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "IMAGEURL")
    private String imageURL;

}
