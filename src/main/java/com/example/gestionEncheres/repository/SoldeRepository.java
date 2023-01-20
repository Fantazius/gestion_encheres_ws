package com.example.gestionEncheres.repository;

import com.example.gestionEncheres.models.Solde;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SoldeRepository extends CrudRepository<Solde, Integer> {
    @Query(value = "select * from soldes where valid=0",nativeQuery = true)
    public List<Solde> getRechargementsAttente();
}
