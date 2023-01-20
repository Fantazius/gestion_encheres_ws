package com.example.gestionEncheres.service;


import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.EnchereGagnant;
import com.example.gestionEncheres.repository.EnchereGagnantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereGagnantService {

    @Autowired(required=true)
    EnchereGagnantRepository enchereGagnantRepository;


    public List<EnchereGagnant> getListEnchereNonFinished()
    {
        return enchereGagnantRepository.ListEnchereFini();

    }

}
