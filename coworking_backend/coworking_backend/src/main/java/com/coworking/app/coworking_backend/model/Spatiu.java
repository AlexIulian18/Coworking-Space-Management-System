package com.coworking.app.coworking_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "spatii")
@Data // Lombok generează automat getTip(), getPretPeOra(), etc.
public class Spatiu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_spatiu")
    private Integer idSpatiu;

    @Column(name = "nume_spatiu")
    private String numeSpatiu;

    @Column(name = "descriere") // Am adăugat coloana descriere pentru a se potrivi cu Repository-ul tău
    private String descriere;

    @ManyToOne
    @JoinColumn(name = "id_tip", referencedColumnName = "id_tip", nullable = false)
    private TipuriSpatii tip;

    @Column(nullable = false)
    private Integer capacitate;

    @Column(name = "pret_pe_ora")
    private Double pretPeOra;

    @Column(nullable = false)
    private Boolean disponibilitate = true;

    @OneToMany(mappedBy = "spatiu")
    @JsonIgnore
    private List<Rezervare> rezervari;

    @OneToMany(mappedBy = "spatiu", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Recenzie> recenzii;

    /**
     * METODĂ AJUTĂTOARE: Rezolvă eroarea din SpatiuService.
     * Deoarece Repository-ul SQL Nativ cere un Integer (idTip), nu un obiect.
     */
    public Integer getIdTip() {
        return (this.tip != null) ? this.tip.getIdTip() : null;
    }
}