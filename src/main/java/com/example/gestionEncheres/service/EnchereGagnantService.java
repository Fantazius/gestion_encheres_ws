package com.example.gestionEncheres.service;


import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.EnchereGagnant;
import com.example.gestionEncheres.repository.EnchereGagnantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnchereGagnantService {

    @Autowired
    EnchereGagnantRepository enchereGagnantRepository;



    public  List<EnchereGagnant> ListHistorique(int idUtilisateur){
        List<EnchereGagnant> encheres = new ArrayList<>();
        enchereGagnantRepository.listHistorique(idUtilisateur).forEach(encheres::add);
        return encheres;
    }


}
