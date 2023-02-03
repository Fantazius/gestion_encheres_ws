package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Gain;
import com.example.gestionEncheres.repository.GainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GainService {
    @Autowired
    GainRepository gainRepository;

    public Gain save(Gain g){
        return gainRepository.save(g);
    }
}
