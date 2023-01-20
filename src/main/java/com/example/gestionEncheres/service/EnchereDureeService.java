package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.EnchereDuree;
import com.example.gestionEncheres.repository.EnchereDureeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnchereDureeService {
    @Autowired(required = true)
    EnchereDureeRepository enchereDureeRepository;

    //getting all enchereDurees record by using the method findaAll() of CrudRepository
    public List<EnchereDuree> getAllEnchereDurees()
    {
        List<EnchereDuree> enchereDurees = new ArrayList<EnchereDuree>();
        enchereDureeRepository.findAll().forEach(enchereDurees::add);
        return enchereDurees;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public EnchereDuree getEnchereDureesById(int id)
    {
        return enchereDureeRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(EnchereDuree enchereDuree)
    {
        enchereDureeRepository.save(enchereDuree);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        enchereDureeRepository.deleteById(id);
    }
    //updating a record
    public void update(EnchereDuree enchereDuree, int bookid)
    {
        enchereDureeRepository.save(enchereDuree);
    }
}
