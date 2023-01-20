package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Solde;
import com.example.gestionEncheres.service.SoldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soldes")
public class SoldeController {
    @Autowired(required=true)
    SoldeService soldeService;
    //creating a get mapping that retrieves all the Solde detail from the database
    @GetMapping()
    private Object getAllSoldes()
    {
        try {
            return new Data(soldeService.getAllSoldes());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific soldes
    @GetMapping("/{soldeid}")
    private Solde getSoldes(@PathVariable("soldeid") int soldeid)
    {
        return soldeService.getSoldesById(soldeid);
    }
    //creating a delete mapping that deletes a specified soldes
    @DeleteMapping("/{soldeid}")
    private void delete(@PathVariable("soldeid") int soldeid)
    {
        soldeService.delete(soldeid);
    }
    //creating post mapping that post the solde detail in the database
    @PostMapping("/add")
    private int save(@RequestBody Solde solde)
    {
        soldeService.saveOrUpdate(solde);
        return solde.getIdSolde();
    }
    //creating put mapping that updates the solde detail
    @PutMapping()
    private Solde update(@RequestBody Solde solde)
    {
        soldeService.saveOrUpdate(solde);
        return solde;
    }
    // ici
    @GetMapping("/attentes")
    private List<Solde> getDepotAttente(){
        return soldeService.getSoldeAnttente();
    }


}
