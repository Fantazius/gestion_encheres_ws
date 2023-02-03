package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Utilisateur;
import com.example.gestionEncheres.service.EnchereService;
import com.example.gestionEncheres.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    EnchereService enchereService;
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
    private Object getUtilisateurs(@PathVariable("utilisateurid") int utilisateurid)
    {
        return new Data(utilisateurService.getUtilisateurByIdAndCorrectMontantSolde(utilisateurid));
    }
    //creating a delete mapping that deletes a specified utilisateur
    @DeleteMapping("/{utilisateurid}")
    private void delete(@PathVariable("utilisateurid") int utilisateurid)
    {
        utilisateurService.delete(utilisateurid);
    }
    //creating post mapping that post the utilisateur detail in the database
    @PostMapping
    private Object save(@RequestBody Utilisateur utilisateur) {
        try {
            utilisateurService.saveOrUpdate(utilisateur);
            return new Data(utilisateur.getIdUtilisateur());
        } catch (Exception e) {
            return new Error(e.getMessage());
        }
    }
    //creating put mapping that updates the utilisateur detail
    @PutMapping()
    private Utilisateur update(@RequestBody Utilisateur utilisateur) throws Exception {
        utilisateurService.saveOrUpdate(utilisateur);
        return utilisateur;
    }

    @PostMapping("/login")
    private Object loginUtilisateur(@RequestBody Utilisateur user) throws Exception {
        try {
            Data data=utilisateurService.login(user);
            System.out.println(data);
            return data;
        } catch (Exception e) {
            return new Error(e.getMessage());
        }
    }

    @GetMapping("/{user_id}/bids")
    private Data getMyBids(@PathVariable("user_id") int id) {
        return new Data(utilisateurService.getMyBids(id));
    }

    @GetMapping("/{user_id}/encheres")
    private Object getEncheresUtilisateur(@PathVariable("user_id") int id) {
        try{
            return new Data(enchereService.getMyEncheres(id));
        }catch (Exception e){
            e.printStackTrace();
            return new Error(e);
        }
    }


}
