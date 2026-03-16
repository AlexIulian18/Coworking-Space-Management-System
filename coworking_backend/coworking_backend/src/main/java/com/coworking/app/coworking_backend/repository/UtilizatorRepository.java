package com.coworking.app.coworking_backend.repository;

import com.coworking.app.coworking_backend.model.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Integer> {

    // 1. Căutare după email (pentru RezervareService)
    Optional<Utilizator> findByEmail(String email);

    // 2. Inserare Nativă
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =
            "INSERT INTO utilizatori (nume, prenume, email, parola, rol, data_inregistrare) " +
                    "VALUES (:nume, :prenume, :email, 'parola_automata', 'CLIENT', CURRENT_DATE)")
    void saveNative(@Param("nume") String nume, @Param("prenume") String prenume, @Param("email") String email);

    // 3. Vizualizare Nativă
    @Query(nativeQuery = true, value = "SELECT * FROM utilizatori")
    List<Utilizator> findAllNative();

    // 4. Autentificare
    @Query(nativeQuery = true, value = "SELECT * FROM utilizatori WHERE email = :email AND parola = :parola")
    Optional<Utilizator> findByEmailAndParolaNative(@Param("email") String email, @Param("parola") String parola);

    // 5. Ștergere Nativă
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM utilizatori WHERE id_utilizator = :id")
    void deleteUtilizatorNative(@Param("id") Integer id);

    // 6. Interogare Complexă: Spații peste medie
    @Query(nativeQuery = true, value =
            "SELECT * FROM utilizatori WHERE id_utilizator IN " +
                    "(SELECT id_utilizator FROM rezervari WHERE id_spatiu IN " +
                    "(SELECT id_spatiu FROM spatii WHERE pret_pe_ora > (SELECT AVG(pret_pe_ora) FROM spatii)))")
    List<Utilizator> findUsersOfExpensiveSpaces();

    // 7. Raport Clienți Premium (> 500 RON)
    @Query(nativeQuery = true, value =
            "SELECT u.* FROM utilizatori u WHERE " +
                    "(SELECT SUM(p.suma) FROM plati p JOIN rezervari r ON p.id_rezervare = r.id_rezervare " +
                    "WHERE r.id_utilizator = u.id_utilizator) > 500")
    List<Utilizator> findPremiumCustomersNative();

    // 8. CĂUTARE DUPĂ NUME (Rezolvă eroarea din React)
    @Query(nativeQuery = true, value = "SELECT * FROM utilizatori WHERE LOWER(nume) LIKE LOWER(CONCAT('%', :nume, '%'))")
    List<Utilizator> findByNumeNative(@Param("nume") String nume);
}