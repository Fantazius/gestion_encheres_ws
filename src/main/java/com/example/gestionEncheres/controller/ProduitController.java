package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Produit;
import com.example.gestionEncheres.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produits")
public class ProduitController {
    @Autowired
    ProduitService produitService;
    //creating a get mapping that retrieves all the Produit detail from the database
    @GetMapping()
    private Object getAllProduits()
    {
        try {
            return new Data(produitService.getAllProduits());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific produits
    @GetMapping("/{produitid}")
    private Produit getProduits(@PathVariable("produitid") int produitid)
    {
        return produitService.getProduitsById(produitid);
    }
    //creating a delete mapping that deletes a specified produits
    @DeleteMapping("/{produitid}")
    private void delete(@PathVariable("produitid") int produitid)
    {
        produitService.delete(produitid);
    }
    //creating post mapping that post the produit detail in the database
    @PostMapping()
    private int save(@RequestBody Produit produit)
    {
        produitService.saveOrUpdate(produit);
        return produit.getIdProduit();
    }
    //creating put mapping that updates the produit detail
    @PutMapping()
    private Produit update(@RequestBody Produit produit)
    {
        produitService.saveOrUpdate(produit);
        return produit;
    }

}
