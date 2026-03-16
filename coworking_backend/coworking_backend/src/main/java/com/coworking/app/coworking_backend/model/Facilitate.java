package com.coworking.app.coworking_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "facilitati")
@Data
public class Facilitate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facilitate")
    private Integer idFacilitate;

    @Column(nullable = false, length = 100)
    private String denumire;
}