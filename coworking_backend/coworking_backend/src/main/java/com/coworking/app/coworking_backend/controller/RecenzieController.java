/**
 * @author Voicu Alexandru Iulian
 * @version 15 Decembrie 2025
 */

package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Recenzie;
import com.coworking.app.coworking_backend.service.RecenzieService; // Importăm Service-ul
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recenzii")
@CrossOrigin(origins = "http://localhost:3000")
public class RecenzieController {

    @Autowired
    private RecenzieService recenzieService; // Injectăm Service-ul, nu Repository-ul

    // 1. Vizualizare - Folosește metoda din Service care apelează SQL Nativ
    @GetMapping
    public List<Recenzie> getAllRecenzii() {
        return recenzieService.findAll();
    }

    // 2. Căutare după parametru variabil (Cerința 6 - Simple)
    // Afișează recenziile pentru un spațiu anume căutat după nume
    @GetMapping("/spatiu/{numeSpatiu}")
    public List<Recenzie> getRecenziiBySpatiu(@PathVariable String numeSpatiu) {
        return recenzieService.findBySpatiuNume(numeSpatiu);
    }

    // 3. Adăugare recenzie nouă (opțional, dacă vrei să permiți din Frontend)
    @PostMapping
    public Recenzie createRecenzie(@RequestBody Recenzie recenzie) {
        return recenzieService.save(recenzie);
    }
}