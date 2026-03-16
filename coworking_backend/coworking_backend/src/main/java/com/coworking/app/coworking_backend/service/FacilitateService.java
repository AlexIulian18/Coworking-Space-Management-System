package com.coworking.app.coworking_backend.service;

import com.coworking.app.coworking_backend.model.Facilitate;
import com.coworking.app.coworking_backend.repository.FacilitateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilitateService {

    @Autowired
    private FacilitateRepository facilitateRepository;

    public List<Facilitate> findAll() {
        return facilitateRepository.findAll();
    }
}