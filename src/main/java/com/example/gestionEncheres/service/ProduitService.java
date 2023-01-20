package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Produit;
import com.example.gestionEncheres.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProduitService {
    @Autowired
    ProduitRepository produitRepository;

    //getting all produits record by using the method findaAll() of CrudRepository
    public List<Produit> getAllProduits()
    {
        List<Produit> produits = new ArrayList<Produit>();
        produitRepository.findAll().forEach(produits::add);
        return produits;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Produit getProduitsById(int id)
    {
        return produitRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Produit produit)
    {
        produitRepository.save(produit);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        produitRepository.deleteById(id);
    }
    //updating a record
    public void update(Produit produit, int bookid)
    {
        produitRepository.save(produit);
    }
}
