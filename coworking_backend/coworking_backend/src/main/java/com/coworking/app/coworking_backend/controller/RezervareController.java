package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Rezervare;
import com.coworking.app.coworking_backend.service.RezervareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rezervari")
@CrossOrigin(origins = "http://localhost:3000")
public class RezervareController {

    @Autowired
    private RezervareService rezervareService;

    // Toate rezervările
    @GetMapping
    public List<Rezervare> getAll() {
        return rezervareService.findAll();
    }

    // CĂUTARE REZERVĂRI DUPĂ EMAIL (Pentru butonul de căutare din front-end)
    @GetMapping("/cauta/{email}")
    public List<Rezervare> searchByEmail(@PathVariable String email) {
        return rezervareService.findByEmail(email);
    }

    // Statistici profit pentru banner-ul din PlatiTable
    @GetMapping("/statistici/profit")
    public Double getProfitTotal() {
        return rezervareService.getProfitTotal();
    }

    // Rezervări pentru spații premium (Subquery pentru Rapoarte)
    @GetMapping("/premium")
    public List<Rezervare> getPremiumReservations() {
        return rezervareService.findPremiumReservations();
    }
}