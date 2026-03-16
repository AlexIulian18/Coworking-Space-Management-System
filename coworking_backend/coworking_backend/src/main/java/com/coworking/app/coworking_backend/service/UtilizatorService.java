package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Utilizator;
import com.coworking.app.coworking_backend.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizatorService {

    @Autowired
    private UtilizatorRepository utilizatorRepository;

    // Returnează toți utilizatorii folosind interogarea nativă
    public List<Utilizator> findAll() {
        return utilizatorRepository.findAllNative();
    }

    // NOU: Metodă pentru căutarea după nume (SQL Nativ)
    public List<Utilizator> findByNume(String nume) {
        return utilizatorRepository.findByNumeNative(nume);
    }

    // Metodă esențială pentru autentificare
    public Optional<Utilizator> authenticate(String email, String parola) {
        return utilizatorRepository.findByEmailAndParolaNative(email, parola);
    }

    // Salvează utilizatorul folosind saveNative din Repository
    @Transactional
    public void save(Utilizator u) {
        utilizatorRepository.saveNative(u.getNume(), u.getPrenume(), u.getEmail());
    }

    // Șterge un utilizator folosind SQL Nativ
    @Transactional
    public void delete(Integer id) {
        utilizatorRepository.deleteUtilizatorNative(id);
    }

    // Returnează lista de clienți VIP
    public List<Utilizator> getPremiumClients() {
        return utilizatorRepository.findPremiumCustomersNative();
    }
}