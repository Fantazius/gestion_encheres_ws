package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Produit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends CrudRepository<Produit,Integer> {
}
