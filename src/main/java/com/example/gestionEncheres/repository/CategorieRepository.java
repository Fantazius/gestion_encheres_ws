package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Categorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie,Integer> {
}
