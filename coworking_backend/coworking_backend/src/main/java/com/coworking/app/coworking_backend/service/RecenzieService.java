/**
 * @author Voicu Alexandru Iulian
 * @version 12 Ianuarie 2026
 */
package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Recenzie;
import com.coworking.app.coworking_backend.repository.RecenzieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecenzieService {

    @Autowired
    private RecenzieRepository recenzieRepository;

    // 1. Modificăm să folosească metoda Nativă (cea cu JOIN-uri)
    public List<Recenzie> findAll() {
        return recenzieRepository.findAllNative();
    }

    // 2. Adăugăm metoda de căutare cerută de Controller (Cerința 6 - Parametru variabil)
    public List<Recenzie> findBySpatiuNume(String numeSpatiu) {
        return recenzieRepository.findRecenziiBySpatiuNumeNative(numeSpatiu);
    }

    // 3. Păstrăm metoda de salvare cu logica de validare
    public Recenzie save(Recenzie recenzie) {
        if (recenzie.getRating() < 1) recenzie.setRating(1);
        if (recenzie.getRating() > 5) recenzie.setRating(5);
        return recenzieRepository.save(recenzie);
    }
}