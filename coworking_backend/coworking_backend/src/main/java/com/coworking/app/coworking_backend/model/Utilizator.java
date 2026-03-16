package com.coworking.app.coworking_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "utilizatori")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizator")
    private Integer idUtilizator;

    @Column(name = "data_inregistrare")
    private LocalDate dataInregistrare;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "prenume")
    private String prenume;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "parola")
    private String parola;

    @Column(name = "rol")
    private String rol;

    @OneToMany(mappedBy = "utilizator", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rezervare> rezervari;

    public Utilizator() {
        this.dataInregistrare = LocalDate.now();
        this.rol = "CLIENT"; // Setăm un rol default pentru a apărea în tabel
    }

    // Getters si Setters
    public Integer getIdUtilizator() { return idUtilizator; }
    public void setIdUtilizator(Integer idUtilizator) { this.idUtilizator = idUtilizator; }
    public LocalDate getDataInregistrare() { return dataInregistrare; }
    public void setDataInregistrare(LocalDate dataInregistrare) { this.dataInregistrare = dataInregistrare; }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public String getPrenume() { return prenume; }
    public void setPrenume(String prenume) { this.prenume = prenume; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getParola() { return parola; }
    public void setParola(String parola) { this.parola = parola; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public List<Rezervare> getRezervari() { return rezervari; }
    public void setRezervari(List<Rezervare> rezervari) { this.rezervari = rezervari; }
}