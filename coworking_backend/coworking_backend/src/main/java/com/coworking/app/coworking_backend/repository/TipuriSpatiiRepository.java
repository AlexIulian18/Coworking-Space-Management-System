/**
 * @author Voicu Alexandru Iulian
 * @version 10 Decembrie 2025
 */

package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.TipuriSpatii;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipuriSpatiiRepository extends JpaRepository<TipuriSpatii, Integer> {

    // VIZUALIZARE NATIVĂ - Returnează toate categoriile de spații (Birou, Sala Sedinte, etc.)
    @Query(nativeQuery = true, value = "SELECT * FROM Tipuri_spatii")
    List<TipuriSpatii> findAllNative();
}
