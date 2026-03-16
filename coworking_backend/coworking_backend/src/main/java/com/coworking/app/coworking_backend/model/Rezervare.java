package com.coworking.app.coworking_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rezervari")
public class Rezervare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rezervare")
    private Integer idRezervare;

    // Am eliminat coloanele simple idUtilizator/idSpatiu pentru a folosi direct obiectele
    // Acest lucru permite salvarea in cascada (Cerința: adaugare indiferent daca exista utilizatorul)

    @Column(name = "data_start")
    private LocalDate dataInceput;

    @Column(name = "data_end")
    private LocalDate dataSfarsit;

    @Column(name = "pret_total")
    private Double pretTotal;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "rezervare", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Plata> plati;

    @ManyToOne(cascade = CascadeType.PERSIST) // Salvează automat utilizatorul dacă e nou
    @JoinColumn(name = "id_utilizator")
    @JsonIgnoreProperties("rezervari")
    private Utilizator utilizator;

    @ManyToOne
    @JoinColumn(name = "id_spatiu")
    @JsonIgnoreProperties("rezervari")
    private Spatiu spatiu;

    public Rezervare() {}

    // Getters și Setters
    public Integer getIdRezervare() { return idRezervare; }
    public void setIdRezervare(Integer idRezervare) { this.idRezervare = idRezervare; }
    public LocalDate getDataInceput() { return dataInceput; }
    public void setDataInceput(LocalDate dataInceput) { this.dataInceput = dataInceput; }
    public LocalDate getDataSfarsit() { return dataSfarsit; }
    public void setDataSfarsit(LocalDate dataSfarsit) { this.dataSfarsit = dataSfarsit; }
    public Double getPretTotal() { return pretTotal; }
    public void setPretTotal(Double pretTotal) { this.pretTotal = pretTotal; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Utilizator getUtilizator() { return utilizator; }
    public void setUtilizator(Utilizator utilizator) { this.utilizator = utilizator; }

    public Spatiu getSpatiu() { return spatiu; }
    public void setSpatiu(Spatiu spatiu) { this.spatiu = spatiu; }
}