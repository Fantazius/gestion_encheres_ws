package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Gagnant;
import com.example.gestionEncheres.repository.GagnantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GagnantService {
    @Autowired
    GagnantRepository gagnantRepository;

    //getting all gagnants record by using the method findaAll() of CrudRepository
    public List<Gagnant> getAllGagnants()
    {
        List<Gagnant> gagnants = new ArrayList<Gagnant>();
        gagnantRepository.findAll().forEach(gagnants::add);
        return gagnants;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Gagnant getGagnantsById(int id)
    {
        return gagnantRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Gagnant gagnant)
    {
        gagnantRepository.save(gagnant);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        gagnantRepository.deleteById(id);
    }
    //updating a record
    public void update(Gagnant gagnant, int bookid)
    {
        gagnantRepository.save(gagnant);
    }
}
