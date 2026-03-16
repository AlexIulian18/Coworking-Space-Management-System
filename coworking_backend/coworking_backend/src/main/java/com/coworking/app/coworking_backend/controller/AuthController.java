/**
 * Clasa pentru gestionarea procesului de autentificare
 * @author Voicu Alexandru Iulian
 * @version 12 Ianuarie 2026
 */

package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.service.UtilizatorService;
import com.coworking.app.coworking_backend.model.Utilizator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.OPTIONS})
public class AuthController {

    @Autowired
    private UtilizatorService utilizatorService;

    public static class LoginRequest {
        public String email;
        public String parola;
    }

    public static class ApiResponse {
        public boolean success;
        public String message;
        public String token;

        public ApiResponse(boolean success, String message) {
            this.success = success;
            this.message = message;
            this.token = success ? "demo-token-12345" : null;
        }
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginRequest request) {
        // Apelăm serviciul care returnează un Optional<Utilizator>
        Optional<Utilizator> authResult = utilizatorService.authenticate(request.email, request.parola);

        // Verificăm dacă Optional-ul conține un utilizator (isPresent)
        if (authResult.isPresent()) {
            Utilizator utilizator = authResult.get(); // Extragem utilizatorul
            return new ApiResponse(true, "Autentificare reușită! Bine ai venit, " + utilizator.getPrenume() + "!");
        } else {
            return new ApiResponse(false, "Credențiale invalide. Verifică email-ul și parola.");
        }
    }
}