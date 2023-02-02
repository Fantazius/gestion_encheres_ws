package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Categorie;
import com.example.gestionEncheres.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie,Integer> {

}
