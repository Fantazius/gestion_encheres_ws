package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.EnchereGagnant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnchereGagnantRepository   extends CrudRepository<EnchereGagnant,String> {


    @Query(nativeQuery = true,value = "SELECT * FROM encheresgagnant")
    public List<EnchereGagnant> ListEnchereFini();

}
