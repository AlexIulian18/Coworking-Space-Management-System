package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.Facilitate;
import com.coworking.app.coworking_backend.service.FacilitateService; // Importăm Service-ul
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facilitati")
@CrossOrigin(origins = "http://localhost:3000")
public class FacilitateController {

    @Autowired
    private FacilitateService facilitateService; // Injectăm Service-ul

    @GetMapping
    public List<Facilitate> getAllFacilitati() {
        return facilitateService.findAll();
    }
}