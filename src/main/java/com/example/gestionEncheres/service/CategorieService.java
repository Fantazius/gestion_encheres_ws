package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Categorie;
import com.example.gestionEncheres.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {
    @Autowired(required=true)
    CategorieRepository categorieRepository;

    //getting all categories record by using the method findaAll() of JpaRepository
    public List<Categorie> getAllCategories()
    {
        List<Categorie> categories = new ArrayList<Categorie>();
        categorieRepository.findAll().forEach(categories::add);
        return categories;
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
