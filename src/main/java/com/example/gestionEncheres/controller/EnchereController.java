package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.*;
import com.example.gestionEncheres.service.EnchereService;
import com.example.gestionEncheres.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/encheres")
public class EnchereController {
    @Autowired
    EnchereService enchereService;
    @Autowired
    PhotoService photoService;
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
    private int addEnchere(@RequestBody EncherePhotos enchere) throws Exception {
        enchereService.save(enchere);
        return 1;
    }

    // ici
    @GetMapping("/search")
    private Object rechercher(@RequestParam(name = "motcle") String motCle, @RequestParam(name = "daty") String daty, @RequestParam(name = "prix") String prix, @RequestParam(name = "status") String status, @RequestParam(name = "categorie") String categorie) throws Exception {
        Date date = null;
        if(daty!=""){
            date = Date.valueOf(daty);
        }
        int p = Integer.valueOf(prix);
        Boolean s = Boolean.valueOf(status);
        int c = Integer.valueOf(categorie);
        try{
            return new Data(enchereService.rechercher(motCle, date, p, s, c));
        }catch (Exception e){
            return new Error(e);
        }
    }

    @GetMapping("/enchereNonFinished")
    private Object getListEnchereNonFinished()
    {
        try {
            return new Data( enchereService.getListEnchereNonFinished());
        }catch (Exception e){
            return new Error(e);
        }

    }

    @GetMapping("/enchereHistorique/{utilisateurid}")
    private Object getListEnchereHistorique(@PathVariable("utilisateurid") int idUtilisateur)
    {
        try {
            return new Data(enchereService.getListEnchereHistorique(idUtilisateur));
        }catch (Exception e){
            return new Error(e);
        }

    }

    @GetMapping("/{idenchere}/photo")
    private Object getPhotoByIdEnchere(@PathVariable("idenchere")Integer idEnchere) throws Exception {
        return new Data(photoService.getPhotoByIdEnchere(idEnchere));
    }
    @GetMapping("/{idenchere}/gagnant")
    private Object getGagnant(@PathVariable("idenchere")Integer idEnchere) throws Exception {
        return new Data(enchereService.getGagnant(idEnchere));
    }

    @PutMapping
    private int finir(@RequestBody Enchere enchere){
        return enchereService.finir(enchere.getIdEnchere());
    }

}
