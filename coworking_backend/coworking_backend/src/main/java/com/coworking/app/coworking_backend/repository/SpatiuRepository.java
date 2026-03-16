/**
 * Repository pentru gestionarea entității Spatiu folosind SQL Nativ.
 * Include operații de CRUD complet și interogări complexe cu subquery-uri.
 * @author Voicu Alexandru Iulian
 * @version 22 Ianuarie 2026
 */

package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Spatiu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SpatiuRepository extends JpaRepository<Spatiu, Integer> {

    // ==========================================
    // 1. INTEROGĂRI DE VIZUALIZARE (SELECT)
    // ==========================================

    // Catalogul spațiilor cu denumirea categoriei (JOIN)
    @Query(nativeQuery = true, value =
            "SELECT s.* FROM Spatii s JOIN Tipuri_spatii ts ON s.id_tip = ts.id_tip")
    List<Spatiu> findAllNative();

    // Identificare spații fără feedback (Subquery cu NOT IN)
    @Query(nativeQuery = true, value =
            "SELECT * FROM Spatii WHERE id_spatiu NOT IN (SELECT id_spatiu FROM Recenzii)")
    List<Spatiu> findSpatiiFaraRecenzii();

    // ==========================================
    // 2. INTEROGĂRI COMPLEXE (SUBQUERIES + PARAMETRI)
    // ==========================================

    // Subquery: Spații cu preț peste media generală
    @Query(nativeQuery = true, value =
            "SELECT * FROM Spatii WHERE pret_pe_ora > (SELECT AVG(pret_pe_ora) FROM Spatii)")
    List<Spatiu> findSpatiiPesteMedie();

    // Subquery + Parametru Variabil: Spații mai scumpe decât media dintr-un anumit tip (ex: 'Birou')
    @Query(nativeQuery = true, value =
            "SELECT * FROM Spatii WHERE id_tip = (SELECT id_tip FROM Tipuri_spatii WHERE denumire = :tipDenumire) " +
                    "AND pret_pe_ora > (SELECT AVG(pret_pe_ora) FROM Spatii)")
    List<Spatiu> findSpatiiPremiumPerTip(@Param("tipDenumire") String tipDenumire);

    // ==========================================
    // 3. OPERAȚII DML NATIVE (INSERT, UPDATE, DELETE)
    // ==========================================

    /**
     * INSERT NATIV - Adăugare spațiu nou manual
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =
            "INSERT INTO Spatii (nume_spatiu, descriere, pret_pe_ora, capacitate, id_tip) " +
                    "VALUES (:nume, :descriere, :pret, :capacitate, :idTip)")
    void insertSpatiuNative(@Param("nume") String nume,
                            @Param("descriere") String descriere,
                            @Param("pret") Double pret,
                            @Param("capacitate") Integer capacitate,
                            @Param("idTip") Integer idTip);

    /**
     * UPDATE NATIV - Modificare preț pentru un spațiu specific
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE Spatii SET pret_pe_ora = :noulPret WHERE id_spatiu = :id")
    int updatePretSpatiuNative(@Param("id") Integer id, @Param("noulPret") Double noulPret);

    /**
     * DELETE NATIV - Ștergere spațiu după ID
     */
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM Spatii WHERE id_spatiu = :id")
    void deleteSpatiuNative(@Param("id") Integer id);
}