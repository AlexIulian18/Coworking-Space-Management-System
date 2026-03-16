package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Plata;
import com.coworking.app.coworking_backend.service.PlataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/plati")
@CrossOrigin(origins = "http://localhost:3000")
public class PlataController {

    @Autowired
    private PlataService plataService;

    @GetMapping
    public List<Plata> getAllPlati() {
        return plataService.findAll();
    }

    // ADĂUGĂ ACEASTĂ METODĂ pentru a salva plata din React
    @PostMapping
    public Plata addPlata(@RequestBody Plata plata) {
        return plataService.save(plata);
    }
}