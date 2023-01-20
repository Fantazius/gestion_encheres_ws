package com.example.gestionEncheres.service;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Admin;
import com.example.gestionEncheres.models.AdminToken;
import com.example.gestionEncheres.models.Token;
import com.example.gestionEncheres.models.Utilisateur;
import com.example.gestionEncheres.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminTokenService adminTokenService;

    //getting all admins record by using the method findaAll() of CrudRepository
    public List<Admin> getAllAdmins()
    {
        List<Admin> admins = new ArrayList<Admin>();
        adminRepository.findAll().forEach(admins::add);
        return admins;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Admin getAdminsById(int id)
    {
        return adminRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Admin admin)
    {
        adminRepository.save(admin);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        adminRepository.deleteById(id);
    }
    //updating a record
    public void update(Admin admin, int bookid)
    {
        adminRepository.save(admin);
    }

    public Data login(Admin admin) throws Exception{
        if (admin.getIdentifiant() == null || admin.getPassword() == null)
            throw new Exception("Veuillez remplir tout les champs");
        else {
            Admin adm = adminRepository.findByIdentifiantAndPassword(admin.getIdentifiant(), DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
            if (adm != null) {
                AdminToken token = new AdminToken();
                token.setToken(AdminTokenService.generateToken(String.valueOf(adm.getIdAdmin())));
                token.setAdmin(adm);
                adminTokenService.saveOrUpdate(token);
                return new Data(adminTokenService.getTokenByToken(token.getToken()));
            } else {
                throw new Exception("Identifiants incorrect");
            }
        }
    }
}
