package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Mise;
import com.example.gestionEncheres.models.MiseToken;
import com.example.gestionEncheres.models.Token;
import com.example.gestionEncheres.service.MiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mises")
public class MiseController {
    @Autowired(required=true)
    MiseService miseService;
    //creating a get mapping that retrieves all the Mise detail from the database
    @GetMapping("/getallmises")
    private Object getAllMises()
    {
        try {
            return new Data(miseService.getAllMises());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific mises
    @GetMapping("/{miseid}")
    private Mise getMises(@PathVariable("miseid") int miseid)
    {
        return miseService.getMisesById(miseid);
    }
    //creating a delete mapping that deletes a specified mises
    @DeleteMapping("/{miseid}")
    private void delete(@PathVariable("miseid") int miseid)
    {
        miseService.delete(miseid);
    }
    // ici
    //creating post mapping that post the mise detail in the database
    @PostMapping("/miser")
    //p0rivate int save(@RequestBody Mise mise, @RequestBody Token token) throws Exception {
    private int save(@RequestBody MiseToken miseToken) throws Exception {
        System.out.println(miseToken.toString());
        miseService.miser(miseToken.getToken(), miseToken.getIdEnchere(), miseToken.getMontant());
        return 1;
    }
    //creating put mapping that updates the mise detail
    @PutMapping()
    private Mise update(@RequestBody Mise mise)
    {
        miseService.saveOrUpdate(mise);
        return mise;
    }

}
