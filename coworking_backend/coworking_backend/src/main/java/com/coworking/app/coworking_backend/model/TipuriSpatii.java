/**
 * @author Voicu Alexandru Iulian
 * @version 10 Decembrie 2025
 */

package com.coworking.app.coworking_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipuri_spatii")
@Data
public class TipuriSpatii {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tip")
    private Integer idTip;

    @Column(nullable = false, unique = true, length = 50)
    private String denumire;
}