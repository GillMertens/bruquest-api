package com.bruquest.bruquestapi.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "USER")
public class User {
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ROLE")
    private String role;

}
