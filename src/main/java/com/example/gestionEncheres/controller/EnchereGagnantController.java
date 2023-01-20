package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.service.EnchereDureeService;
import com.example.gestionEncheres.service.EnchereGagnantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encheresFinished")
public class EnchereGagnantController {

    @Autowired(required=true)
    EnchereGagnantService enchereGagnantService;
    @GetMapping("")
    private Object getListEnchereFinished()
    {
        try {
            return new Data(enchereGagnantService.getListEnchereNonFinished());
        }catch (Exception e){
            return new Error(e);
        }
    }
}
