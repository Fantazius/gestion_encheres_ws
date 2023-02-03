package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.EnchereGagnant;
import com.example.gestionEncheres.service.EnchereGagnantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/encheresgagnant")
public class EnchereGagnantController {

    @Autowired
    EnchereGagnantService enchereGagnantService;

    @GetMapping("/enchereHistorique/{utilisateurid}")
    private Object getListEnchereHistorique(@PathVariable("utilisateurid") int idUtilisateur)
    {
        try{
            return new Data(enchereGagnantService.ListHistorique(idUtilisateur));
        }catch (Exception e){
            return  new Error(e);

        }
    }


}
