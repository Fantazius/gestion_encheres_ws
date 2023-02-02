package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Categorie;
import com.example.gestionEncheres.models.Produit;
import com.example.gestionEncheres.repository.CategorieRepository;
import com.example.gestionEncheres.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CategorieService {
    @Autowired(required=true)
    CategorieRepository categorieRepository;
    @Autowired
    ProduitRepository produitRepository;

    //getting all categories record by using the method findaAll() of JpaRepository
    public List<Categorie> getAllCategories()
    {
        return categorieRepository.findAll();
    }
    public List<Categorie> getAllCategoriesWithProduits(){
        HashMap<String,Object> map=new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        ArrayList<List<Produit>> produits=new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            produits.add(produitRepository.getProduitsByIdCategorie(categories.get(i).getIdCategorie()));
        }
        return categories;
    }

    public HashMap<String,Object> getAllCategoriesMapWithProduits(){
        HashMap<String,Object> map=new HashMap<>();
        List<Categorie> categories=categorieRepository.findAll();
        ArrayList<List<Produit>> produits=new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            produits.add(produitRepository.getProduitsByIdCategorie(categories.get(i).getIdCategorie()));
        }
        map.put("categories",categories);
        map.put("produits",produits);
        return map;
    }
    public List<Produit> getProduitByIdCategorie(Integer idCategorie){
        return produitRepository.getProduitsByIdCategorie(idCategorie);
    }
    //getting a specific record by using the method findById() of JpaRepository
    public Categorie getCategoriesById(int id)
    {
        return categorieRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of JpaRepository
    public void saveOrUpdate(Categorie categorie)
    {
        categorieRepository.save(categorie);
    }
    //deleting a specific record by using the method deleteById() of JpaRepository
    public void delete(int id)
    {
        categorieRepository.deleteById(id);
    }
    //updating a record
    public void update(Categorie categorie, int bookid)
    {
        categorieRepository.save(categorie);
    }
}
