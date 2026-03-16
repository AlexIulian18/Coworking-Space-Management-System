package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Facilitate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pentru Facilitati - Implementare SQL Nativ.
 * @author Voicu Alexandru Iulian
 * @version 21 Ianuarie 2026
 */
@Repository
public interface FacilitateRepository extends JpaRepository<Facilitate, Integer> {

    // 1. VIZUALIZARE NATIVĂ (Toate facilitățile: WiFi, Cafea, etc.)
    @Query(value = "SELECT * FROM facilitati", nativeQuery = true)
    List<Facilitate> findAllNative();

    // 2. JOIN Nativ: Găsește facilitățile disponibile pentru un anumit spațiu
    // Presupunând că ai o tabelă de legătură (Many-to-Many) numită spatii_facilitati
    @Query(value = "SELECT f.* FROM facilitati f " +
            "JOIN spatii_facilitati sf ON f.id_facilitate = sf.id_facilitate " +
            "WHERE sf.id_spatiu = :idSpatiu", nativeQuery = true)
    List<Facilitate> findBySpatiuNative(@Param("idSpatiu") Integer idSpatiu);

    // 3. SUBQUERY Complex (Cerința 5): Facilitățile care sunt prezente în TOATE spațiile premium
    @Query(value = "SELECT * FROM facilitati WHERE id_facilitate IN " +
            "(SELECT id_facilitate FROM spatii_facilitati WHERE id_spatiu IN " +
            "(SELECT id_spatiu FROM spatii WHERE pret_pe_ora > (SELECT AVG(pret_pe_ora) FROM spatii)))",
            nativeQuery = true)
    List<Facilitate> findFacilitatiPremiumNative();
}