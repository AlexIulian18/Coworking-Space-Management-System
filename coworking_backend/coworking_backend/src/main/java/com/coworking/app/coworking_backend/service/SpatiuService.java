/**
 * Service pentru gestionarea spațiilor folosind exclusiv metode SQL Native.
 * @author Voicu Alexandru Iulian
 * @version 22 Ianuarie 2026
 */
package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Spatiu;
import com.coworking.app.coworking_backend.repository.SpatiuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpatiuService {

    @Autowired
    private SpatiuRepository spatiuRepository;

    // 1. Vizualizare (SELECT Nativ cu JOIN)
    public List<Spatiu> findAll() {
        return spatiuRepository.findAllNative();
    }

    // 2. Filtrare cu Parametru Variabil (Subquery)
    public List<Spatiu> findByTip(String denumireTip) {
        return spatiuRepository.findSpatiiPremiumPerTip(denumireTip);
    }

    // 3. Interogare Complexă (Subquery: Preț peste medie)
    public List<Spatiu> getPremiumSpaces() {
        return spatiuRepository.findSpatiiPesteMedie();
    }

    // 4. Interogare Complexă (Subquery: NOT IN)
    public List<Spatiu> getSpacesWithNoReviews() {
        return spatiuRepository.findSpatiiFaraRecenzii();
    }

    /**
     * MODIFICARE: Salvare folosind INSERT Nativ.
     * Rezolvă posibila eroare "cannot find symbol" prin extragerea ID-ului corect.
     */
    @Transactional
    public void save(Spatiu spatiu) {
        // Verificăm dacă idTip este un obiect sau un Integer simplu în model
        // Dacă este obiect, folosim: spatiu.getTip().getIdTip()
        // Dacă este Integer simplu, folosim: spatiu.getIdTip()

        spatiuRepository.insertSpatiuNative(
                spatiu.getNumeSpatiu(),
                spatiu.getDescriere(),
                spatiu.getPretPeOra(),
                spatiu.getCapacitate(),
                spatiu.getIdTip()
        );
    }

    /**
     * MODIFICARE: Actualizare preț folosind UPDATE Nativ.
     */
    @Transactional
    public void updatePret(Integer id, Double noulPret) {
        spatiuRepository.updatePretSpatiuNative(id, noulPret);
    }

    /**
     * MODIFICARE: Ștergere folosind DELETE Nativ.
     */
    @Transactional
    public void delete(Integer id) {
        spatiuRepository.deleteSpatiuNative(id);
    }
}