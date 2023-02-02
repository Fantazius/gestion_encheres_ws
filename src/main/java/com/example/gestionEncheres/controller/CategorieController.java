package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Categorie;
import com.example.gestionEncheres.models.Produit;
import com.example.gestionEncheres.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {
    @Autowired(required=true)
    CategorieService categorieService;
    //creating a get mapping that retrieves all the Categorie detail from the database
    @GetMapping()
    private Object getAllCategories()
    {
        try {
            return new Data(categorieService.getAllCategories());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific categories
    @GetMapping("/produits")
    private Object getAllCategoriesWithProduits(){
        try {
            return new Data(categorieService.getAllCategoriesMapWithProduits());
        }catch (Exception e){
            return new Error(e);
        }
    }
    @GetMapping("/{categorieid}")
    private Categorie getCategories(@PathVariable("categorieid") int categorieid)
    {
        return categorieService.getCategoriesById(categorieid);
    }

    @GetMapping("/{categorieid}/produits")
    private List<Produit> getProduitsByCategorieId(@PathVariable("categorieid") int categorieid)
    {
        return categorieService.getProduitByIdCategorie(categorieid);
    }
    /*@GetMapping("/{categorieid}/produits")
    private Produit getProduits(@PathVariable("categorieid")int categorieid){
            return categorieService.get
    }*/
    //creating a delete mapping that deletes a specified categories
    @DeleteMapping("/{categorieid}")
    private void delete(@PathVariable("categorieid") int categorieid)
    {
        categorieService.delete(categorieid);
    }
    //creating post mapping that post the categorie detail in the database
    @PostMapping()
    private int save(@RequestBody Categorie categorie)
    {
        categorieService.saveOrUpdate(categorie);
        return categorie.getIdCategorie();
    }
    //creating put mapping that updates the categorie detail
    @PutMapping()
    private Categorie update(@RequestBody Categorie categorie)
    {
        categorieService.saveOrUpdate(categorie);
        return categorie;
    }

}
