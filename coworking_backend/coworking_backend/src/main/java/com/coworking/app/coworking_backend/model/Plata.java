package com.coworking.app.coworking_backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "plati")
public class Plata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plata")
    private Integer idPlata;

    @Column(name = "suma")
    private Double suma;

    @Column(name = "metoda_plata")
    private String metodaPlata;

    @Column(name = "data_plata")
    private LocalDateTime dataPlata;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rezervare") // Am scos insertable/updatable false pentru a permite salvarea
    private Rezervare rezervare;

    public Plata() {}

    // Getters și Setters
    public Integer getIdPlata() { return idPlata; }
    public void setIdPlata(Integer idPlata) { this.idPlata = idPlata; }
    public Double getSuma() { return suma; }
    public void setSuma(Double suma) { this.suma = suma; }
    public String getMetodaPlata() { return metodaPlata; }
    public void setMetodaPlata(String metodaPlata) { this.metodaPlata = metodaPlata; }
    public LocalDateTime getDataPlata() { return dataPlata; }
    public void setDataPlata(LocalDateTime dataPlata) { this.dataPlata = dataPlata; }
    public Rezervare getRezervare() { return rezervare; }
    public void setRezervare(Rezervare rezervare) { this.rezervare = rezervare; }
}