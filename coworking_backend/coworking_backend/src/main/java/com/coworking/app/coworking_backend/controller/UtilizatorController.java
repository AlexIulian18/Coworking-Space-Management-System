package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Utilizator;
import com.coworking.app.coworking_backend.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilizatori")
@CrossOrigin(origins = "http://localhost:3000") // Permite cererile de la aplicația React
public class UtilizatorController {

    @Autowired
    private UtilizatorService utilizatorService;

    // 1. Obține toți utilizatorii (Folosește findAllNative din Service)
    @GetMapping
    public List<Utilizator> getAll() {
        return utilizatorService.findAll();
    }

    // 2. CĂUTARE DUPĂ NUME (Rezolvă eroarea "Eroare la căutare!" din React)
    // Această metodă răspunde la: http://localhost:8081/api/utilizatori/cauta/{nume}
    @GetMapping("/cauta/{nume}")
    public List<Utilizator> search(@PathVariable String nume) {
        return utilizatorService.findByNume(nume);
    }

    // 3. Adăugare Utilizator (Folosește saveNative din Service)
    @PostMapping
    public ResponseEntity<String> addUtilizator(@RequestBody Utilizator utilizator) {
        try {
            utilizatorService.save(utilizator);
            return ResponseEntity.ok("Utilizator salvat cu succes în baza de date!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Eroare la salvare: " + e.getMessage());
        }
    }

    // 4. Ștergere Utilizator (Folosește deleteUtilizatorNative din Service)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        try {
            utilizatorService.delete(id);
            return ResponseEntity.ok("Utilizator șters cu succes!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Eroare la ștergere: " + e.getMessage());
        }
    }

    // 5. Raport Clienți Premium (Subquery SQL Nativ)
    @GetMapping("/premium")
    public List<Utilizator> getPremium() {
        return utilizatorService.getPremiumClients();
    }
}