package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Solde;
import com.example.gestionEncheres.repository.SoldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoldeService {
    @Autowired
    SoldeRepository soldeRepository;

    //getting all soldes record by using the method findaAll() of CrudRepository
    public List<Solde> getAllSoldes()
    {
        List<Solde> soldes = new ArrayList<Solde>();
        soldeRepository.findAll().forEach(soldes::add);
        return soldes;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Solde getSoldesById(int id)
    {
        return soldeRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Solde solde)
    {
        solde.setDateDepot(new Timestamp(System.currentTimeMillis()));
        soldeRepository.save(solde);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        soldeRepository.deleteById(id);
    }
    //updating a record
    public void update(Solde solde, int bookid)
    {
        soldeRepository.save(solde);
    }

    public void valider(Solde solde){
        solde.setValid(1);
        soldeRepository.save(solde);
    }

    public void refuser(Solde solde){
        solde.setValid(-1);
        soldeRepository.save(solde);
    }

    public List<Solde> getSoldeAnttente(){
        return soldeRepository.getRechargementsAttente();
    }
}
