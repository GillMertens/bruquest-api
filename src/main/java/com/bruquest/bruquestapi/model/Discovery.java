package com.bruquest.bruquestapi.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "DISCOVERY")
public class Discovery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "LANDMARK_ID")
    private Landmark landmark;

    @Column(name = "DATE")
    private LocalDateTime date;

    @Column(name = "IMAGEURL")
    private String imageURL;
}
