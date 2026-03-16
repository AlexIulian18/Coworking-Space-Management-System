package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Rezervare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RezervareRepository extends JpaRepository<Rezervare, Integer> {

    // Pentru căutarea după email în tabelul de Rezervări
    @Query(nativeQuery = true, value = "SELECT r.* FROM rezervari r JOIN utilizatori u ON r.id_utilizator = u.id_utilizator WHERE u.email LIKE %:email%")
    List<Rezervare> findByUtilizatorEmailNative(@Param("email") String email);

    // Pentru Rapoarte Complexe: Rezervări unde prețul spațiului > media
    @Query(nativeQuery = true, value = "SELECT * FROM rezervari WHERE id_spatiu IN (SELECT id_spatiu FROM spatii WHERE pret_pe_ora > (SELECT AVG(pret_pe_ora) FROM spatii))")
    List<Rezervare> findPremiumReservationsNative();
}