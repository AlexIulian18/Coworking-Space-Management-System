/**
 * @author Voicu Alexandru Iulian
 * @version 14 Decembrie 2025
 */

package com.coworking.app.coworking_backend.controller;

import com.coworking.app.coworking_backend.model.TipuriSpatii;
import com.coworking.app.coworking_backend.service.TipuriSpatiiService; // Folosim Service-ul creat anterior
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipuri-spatii")
@CrossOrigin(origins = "http://localhost:3000")
public class TipuriSpatiiController {

    @Autowired
    private TipuriSpatiiService tipuriSpatiiService; // Injectăm Service-ul

    // Vizualizare prin Service -> Repository (SQL Nativ)
    @GetMapping
    public List<TipuriSpatii> getAllTipuriSpatii() {
        return tipuriSpatiiService.findAll();
    }
}