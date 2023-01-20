package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.service.EnchereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/encheres")
public class EnchereController {
    @Autowired(required=true)
    EnchereService enchereService;
    //creating a get mapping that retrieves all the Enchere detail from the database
    @GetMapping()
    private Object getAllEncheres()
    {
        try {
            return new Data(enchereService.getAllEncheres());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific encheres
    @GetMapping("/{enchereid}")
    private Enchere getEncheres(@PathVariable("enchereid") int enchereid)
    {
        return enchereService.getEncheresById(enchereid);
    }
    //creating a delete mapping that deletes a specified encheres
    @DeleteMapping("/{enchereid}")
    private void delete(@PathVariable("enchereid") int enchereid)
    {
        enchereService.delete(enchereid);
    }
    //creating post mapping that post the enchere detail in the database

    // ici
    @PostMapping
    private void addEnchere(@RequestBody Enchere enchere) throws Exception {
        enchereService.addEnchere(enchere);
    }

    // ici
    @GetMapping("/search")
    private List<Enchere> rechercher(@RequestParam(name = "motcle") String motCle, @RequestParam(name = "daty") String daty, @RequestParam(name = "prix") String prix, @RequestParam(name = "status") String status, @RequestParam(name = "categorie") String categorie) throws Exception {
        Date date = null;
        if(daty!=""){
            date = Date.valueOf(daty);
        }
        int p = Integer.valueOf(prix);
        Boolean s = Boolean.valueOf(status);
        int c = Integer.valueOf(categorie);
        return enchereService.rechercher(motCle, date, p, s, c);
    }

}
