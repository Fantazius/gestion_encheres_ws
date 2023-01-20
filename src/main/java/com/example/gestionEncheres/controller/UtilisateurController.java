package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Utilisateur;
import com.example.gestionEncheres.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    //creating a get mapping that retrieves all the Utilisateur detail from the database
    @GetMapping()
    private Object getAllUtilisateurs()
    {
        try {
            return new Data(utilisateurService.getAllUtilisateurs());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific utilisateur
    @GetMapping("/{utilisateurid}")
    private Utilisateur getUtilisateurs(@PathVariable("utilisateurid") int utilisateurid)
    {
        return utilisateurService.getUtilisateursById(utilisateurid);
    }
    //creating a delete mapping that deletes a specified utilisateur
    @DeleteMapping("/{utilisateurid}")
    private void delete(@PathVariable("utilisateurid") int utilisateurid)
    {
        utilisateurService.delete(utilisateurid);
    }
    //creating post mapping that post the utilisateur detail in the database
    @PostMapping("/add")
    private int save(@RequestBody Utilisateur utilisateur) throws Exception {
        utilisateurService.saveOrUpdate(utilisateur);
        return utilisateur.getIdUtilisateur();
    }
    //creating put mapping that updates the utilisateur detail
    @PutMapping()
    private Utilisateur update(@RequestBody Utilisateur utilisateur) throws Exception {
        utilisateurService.saveOrUpdate(utilisateur);
        return utilisateur;
    }

    // ici
    @PostMapping("/login")
    private Data loginUtilisateur(@RequestBody Utilisateur user) throws Exception {
        return utilisateurService.login(user);
    }

    // ici
    @GetMapping("/{user_id}/bids")
    private Data getMyBids(@PathVariable("user_id") int id) {
        return new Data(utilisateurService.getMyBids(id));
    }
}
