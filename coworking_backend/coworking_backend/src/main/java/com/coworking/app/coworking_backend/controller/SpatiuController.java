/**
 * Controller pentru gestionarea resurselor de tip Spatiu.
 * Coordonează fluxul între interfața React și baza de date folosind interogări SQL Native.
 * @author Voicu Alexandru Iulian
 * @version 22 Ianuarie 2026
 */

package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Spatiu;
import com.coworking.app.coworking_backend.service.SpatiuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spatii")
@CrossOrigin(origins = "http://localhost:3000")
public class SpatiuController {

    @Autowired
    private SpatiuService spatiuService;

    // --- 1. VIZUALIZARE (SELECT NATIV) ---

    @GetMapping
    public List<Spatiu> getAllSpatii() {
        return spatiuService.findAll();
    }

    // --- 2. INTEROGĂRI COMPLEXE (SUBQUERIES) ---

    @GetMapping("/premium")
    public List<Spatiu> getPremiumSpaces() {
        return spatiuService.getPremiumSpaces();
    }

    @GetMapping("/fara-recenzii")
    public List<Spatiu> getSpacesWithNoReviews() {
        return spatiuService.getSpacesWithNoReviews();
    }

    @GetMapping("/tip/{denumire}")
    public List<Spatiu> getByTip(@PathVariable String denumire) {
        return spatiuService.findByTip(denumire);
    }

    // --- 3. OPERAȚII DML (INSERT, UPDATE, DELETE) ---

    /**
     * Creare spațiu prin INSERT Nativ.
     */
    @PostMapping
    public ResponseEntity<String> createSpatiu(@RequestBody Spatiu spatiu) {
        try {
            // Validare simplă în Controller
            if (spatiu.getPretPeOra() == null || spatiu.getPretPeOra() < 0) {
                return ResponseEntity.badRequest().body("Eroare: Prețul trebuie să fie o valoare pozitivă.");
            }
            spatiuService.save(spatiu);
            return ResponseEntity.ok("Spațiul a fost inserat cu succes prin SQL Nativ!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Eroare la inserare: " + e.getMessage());
        }
    }

    /**
     * Actualizare preț prin UPDATE Nativ.
     * Parametrul 'noulPret' este primit ca valoare simplă în corpul cererii.
     */
    @PutMapping("/{id}/pret")
    public ResponseEntity<String> updatePret(@PathVariable Integer id, @RequestBody Double noulPret) {
        try {
            spatiuService.updatePret(id, noulPret);
            return ResponseEntity.ok("Prețul a fost actualizat cu succes prin SQL Nativ!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Eroare la actualizare: " + e.getMessage());
        }
    }

    /**
     * Ștergere prin DELETE Nativ.
     * Include tratarea erorilor de integritate (Foreign Key).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSpatiu(@PathVariable Integer id) {
        try {
            spatiuService.delete(id);
            return ResponseEntity.ok("Spațiul a fost eliminat din baza de date prin SQL Nativ!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Eroare: Nu se poate șterge spațiul deoarece are rezervări sau recenzii active.");
        }
    }
}