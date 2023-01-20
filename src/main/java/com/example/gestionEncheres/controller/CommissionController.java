package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Commission;
import com.example.gestionEncheres.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commissions")
public class CommissionController {
    @Autowired(required=true)
    CommissionService commissionService;
    //creating a get mapping that retrieves all the Commission detail from the database
    @GetMapping()
    private Object getAllCommissions()
    {
        try {
            return new Data(commissionService.getAllCommissions());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific commissions
    @GetMapping("/{commissionid}")
    private Commission getCommissions(@PathVariable("commissionid") int commissionid)
    {
        return commissionService.getCommissionsById(commissionid);
    }
    //creating a delete mapping that deletes a specified commissions
    @DeleteMapping("/{commissionid}")
    private void delete(@PathVariable("commissionid") int commissionid)
    {
        commissionService.delete(commissionid);
    }
    //creating post mapping that post the commission detail in the database
    @PostMapping()
    private int save(@RequestBody Commission commission)
    {
        commissionService.saveOrUpdate(commission);
        return commission.getIdCommission();
    }
    //creating put mapping that updates the commission detail
    @PutMapping()
    private Commission update(@RequestBody Commission commission)
    {
        commissionService.saveOrUpdate(commission);
        return commission;
    }

}
