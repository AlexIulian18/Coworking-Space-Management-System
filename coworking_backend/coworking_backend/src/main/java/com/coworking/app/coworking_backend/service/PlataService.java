package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Plata;
import com.coworking.app.coworking_backend.repository.PlataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PlataService {

    @Autowired
    private PlataRepository plataRepository;

    /**
     * Returnează istoricul tuturor plăților.
     */
    public List<Plata> findAll() {
        return plataRepository.findAll();
    }

    /**
     * Salvează o plată nouă.
     * Rezolvă eroarea de compilare din Controller.
     */
    @Transactional
    public Plata save(Plata plata) {
        // Folosim metoda standard save din JpaRepository
        return plataRepository.save(plata);
    }
}