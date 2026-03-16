/**
 * @author Voicu Alexandru Iulian
 * @version 12 Ianuarie 2026
 */

package com.coworking.app.coworking_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "recenzii")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Recenzie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recenzie")
    private Integer idRecenzie;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilizator")
    private Utilizator utilizator;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_spatiu")
    private Spatiu spatiu;

    private Integer rating;

    private String comentariu;

    @Column(name = "data_recenzii")
    private LocalDate dataRecenzii;
}