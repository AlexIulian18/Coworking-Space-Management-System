/**
 * @author Voicu Alexandru Iulian
 * @version 12 Ianuarie 2026
 */

package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.TipuriSpatii;
import com.coworking.app.coworking_backend.repository.TipuriSpatiiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipuriSpatiiService {

    @Autowired
    private TipuriSpatiiRepository tipuriSpatiiRepository;

    public List<TipuriSpatii> findAll() {
        return tipuriSpatiiRepository.findAll();
    }
}