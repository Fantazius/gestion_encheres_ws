package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.EnchereGagnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnchereGagnantRepository   extends CrudRepository<EnchereGagnant,String> {


    @Query(nativeQuery = true,value = "SELECT * FROM encheresgagnant")
    public List<EnchereGagnant> ListEnchereFini();

}
