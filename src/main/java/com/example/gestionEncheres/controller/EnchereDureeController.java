package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.EnchereDuree;
import com.example.gestionEncheres.service.EnchereDureeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enchereDurees")
public class EnchereDureeController {
    @Autowired
    EnchereDureeService enchereDureeService;
    //creating a get mapping that retrieves all the EnchereDuree detail from the database
    @GetMapping()
    private Object getAllEnchereDurees()
    {
        try {
            return new Data(enchereDureeService.getAllEnchereDurees());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific enchereDurees
    @GetMapping("/{enchereDureeid}")
    private EnchereDuree getEnchereDurees(@PathVariable("enchereDureeid") int enchereDureeid)
    {
        return enchereDureeService.getEnchereDureesById(enchereDureeid);
    }
    //creating a delete mapping that deletes a specified enchereDurees
    @DeleteMapping("/{enchereDureeid}")
    private void delete(@PathVariable("enchereDureeid") int enchereDureeid)
    {
        enchereDureeService.delete(enchereDureeid);
    }
    //creating post mapping that post the enchereDuree detail in the database
    @PostMapping()
    private int save(@RequestBody EnchereDuree enchereDuree)
    {
        enchereDureeService.saveOrUpdate(enchereDuree);
        return enchereDuree.getIdDuree();
    }
    //creating put mapping that updates the enchereDuree detail
    @PutMapping()
    private EnchereDuree update(@RequestBody EnchereDuree enchereDuree)
    {
        enchereDureeService.saveOrUpdate(enchereDuree);
        return enchereDuree;
    }

}
