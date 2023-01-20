package com.example.gestionEncheres.controller;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Admin;
import com.example.gestionEncheres.models.Utilisateur;
import com.example.gestionEncheres.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    @Autowired(required=true)
    AdminService adminService;
    //creating a get mapping that retrieves all the Admin detail from the database
    @GetMapping()
    private Object getAllAdmins()
    {
        try {
            return new Data(adminService.getAllAdmins());
        }catch (Exception e){
            return new Error(e);
        }
    }
    //creating a get mapping that retrieves the detail of a specific admins
    @GetMapping("/{adminid}")
    private Admin getAdmins(@PathVariable("adminid") int adminid)
    {
        return adminService.getAdminsById(adminid);
    }
    //creating a delete mapping that deletes a specified admins
    @DeleteMapping("/{adminid}")
    private void delete(@PathVariable("adminid") int adminid)
    {
        adminService.delete(adminid);
    }
    //creating post mapping that post the admin detail in the database
    @PostMapping()
    private int save(@RequestBody Admin admin)
    {
        adminService.saveOrUpdate(admin);
        return admin.getIdAdmin();
    }
    //creating put mapping that updates the admin detail
    @PutMapping()
    private Admin update(@RequestBody Admin admin)
    {
        adminService.saveOrUpdate(admin);
        return admin;
    }

    // ici
    @PostMapping("/login")
    private Data loginAdmin(@RequestBody Admin admin) throws Exception {
        return adminService.login(admin);
    }
}
