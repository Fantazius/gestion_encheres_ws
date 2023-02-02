package com.example.gestionEncheres.repository;
import com.example.gestionEncheres.models.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit,Integer> {
    @Query(value = "select * from produits where idcategorie=?1",nativeQuery = true)
    List<Produit> getProduitsByIdCategorie(Integer idCategorie);
}
