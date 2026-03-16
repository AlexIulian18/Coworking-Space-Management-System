package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Rezervare;
import com.coworking.app.coworking_backend.repository.RezervareRepository;
import com.coworking.app.coworking_backend.repository.PlataRepository; // Pentru profit
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RezervareService {

    @Autowired
    private RezervareRepository rezervareRepository;

    @Autowired
    private PlataRepository plataRepository;

    public List<Rezervare> findAll() {
        return rezervareRepository.findAll();
    }

    // Rezolvă eroarea 'cannot find symbol method findByEmail'
    public List<Rezervare> findByEmail(String email) {
        return rezervareRepository.findByUtilizatorEmailNative(email);
    }

    // Rezolvă eroarea pentru banner-ul de profit
    public Double getProfitTotal() {
        return plataRepository.getProfitTotalComplex();
    }

    // Rezolvă eroarea 'cannot find symbol method findPremiumReservations'
    public List<Rezervare> findPremiumReservations() {
        return rezervareRepository.findPremiumReservationsNative();
    }
}