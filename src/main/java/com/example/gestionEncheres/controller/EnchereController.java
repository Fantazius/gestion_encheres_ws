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
    @Autowired(required=true)
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
    private List<Enchere> rechercher(@RequestParam(name = "motcle") String motCle, @RequestParam(name = "daty") String daty, @RequestParam(name = "prix") String prix, @RequestParam(name = "status") String status, @RequestParam(name = "categorie") String categorie) throws Exception {
        Date date = null;
        if(daty!=""){
            date = Date.valueOf(daty);
        }
        int p = Integer.parseInt(prix);
        boolean s = Boolean.parseBoolean(status);
        int c = Integer.parseInt(categorie);
        return enchereService.rechercher(motCle, date, p, s, c);
    }

    @GetMapping("/{idenchere}/photo")
    private Photo getPhotoByIdEnchere(@PathVariable("idenchere")Integer idEnchere) throws Exception {
        return photoService.getPhotoByIdEnchere(idEnchere);
    }
    @GetMapping("/{idenchere}/gagnant")
    private Mise getGagnant(@PathVariable("idenchere")Integer idEnchere) throws Exception {
        return enchereService.getGagnant(idEnchere);
    }

    @PutMapping
    private int finir(@RequestBody Enchere enchere){
        return enchereService.finir(enchere.getIdEnchere());
    }

}
