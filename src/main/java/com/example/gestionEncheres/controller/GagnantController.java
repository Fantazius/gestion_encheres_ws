package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Gagnant;
import com.example.gestionEncheres.service.GagnantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gagnants")
public class GagnantController {
    @Autowired
    GagnantService gagnantService;
    //creating a get mapping that retrieves all the Gagnant detail from the database
    @GetMapping()
    private Object getAllGagnants()
    {
        try {
            return new Data(gagnantService.getAllGagnants());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific gagnants
    @GetMapping("/{gagnantid}")
    private Gagnant getGagnants(@PathVariable("gagnantid") int gagnantid)
    {
        return gagnantService.getGagnantsById(gagnantid);
    }
    //creating a delete mapping that deletes a specified gagnants
    @DeleteMapping("/{gagnantid}")
    private void delete(@PathVariable("gagnantid") int gagnantid)
    {
        gagnantService.delete(gagnantid);
    }
    //creating post mapping that post the gagnant detail in the database
    @PostMapping()
    private int save(@RequestBody Gagnant gagnant)
    {
        gagnantService.saveOrUpdate(gagnant);
        return gagnant.getIdEnchere();
    }
    //creating put mapping that updates the gagnant detail
    @PutMapping()
    private Gagnant update(@RequestBody Gagnant gagnant)
    {
        gagnantService.saveOrUpdate(gagnant);
        return gagnant;
    }

}
