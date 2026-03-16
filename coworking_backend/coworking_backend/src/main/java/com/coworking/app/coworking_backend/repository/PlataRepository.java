package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Plata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlataRepository extends JpaRepository<Plata, Integer> {

    // Vizualizare toate plățile (SQL Nativ)
    @Query(nativeQuery = true, value = "SELECT * FROM plati")
    List<Plata> findAllNative();

    // Calcul profit total pentru banner-ul de sus
    @Query(nativeQuery = true, value = "SELECT COALESCE(SUM(suma), 0) FROM plati")
    Double getProfitTotalComplex();

    // Interogare pentru grafic sau statistici (opțional)
    @Query(nativeQuery = true, value = "SELECT * FROM plati ORDER BY data_plata DESC")
    List<Plata> findRecentTransactions();
}