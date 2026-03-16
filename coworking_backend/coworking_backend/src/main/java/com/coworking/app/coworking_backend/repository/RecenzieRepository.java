/**
 * @author Voicu Alexandru Iulian
 * @version 10 Decembrie 2025
 */

package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Recenzie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecenzieRepository extends JpaRepository<Recenzie, Integer> {

    // 1. VIZUALIZARE NATIVĂ (JOIN) - Afișează recenziile cu numele spațiului și al utilizatorului
    @Query(nativeQuery = true, value =
            "SELECT r.* FROM Recenzii r " +
                    "JOIN Spatii s ON r.id_spatiu = s.id_spatiu " +
                    "JOIN Utilizatori u ON r.id_utilizator = u.id_utilizator")
    List<Recenzie> findAllNative();

    // 2. PARAMETRU VARIABIL (Cerința 6 Simple) - Recenzii pentru un anumit spațiu după nume
    @Query(nativeQuery = true, value =
            "SELECT r.* FROM Recenzii r " +
                    "JOIN Spatii s ON r.id_spatiu = s.id_spatiu " +
                    "WHERE s.nume_spatiu = :numeSpatiu")
    List<Recenzie> findRecenziiBySpatiuNumeNative(@Param("numeSpatiu") String numeSpatiu);

    // 3. INTEROGARE COMPLEXĂ (Subquery + Agregare) - Recenziile cu rating peste medie
    @Query(nativeQuery = true, value =
            "SELECT * FROM Recenzii WHERE rating > (SELECT AVG(rating) FROM Recenzii)")
    List<Recenzie> findBestReviewsNative();
}