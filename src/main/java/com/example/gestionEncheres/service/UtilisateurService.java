package com.example.gestionEncheres.service;

import com.example.gestionEncheres.format.Data;
import com.example.gestionEncheres.models.Token;
import com.example.gestionEncheres.models.Utilisateur;
import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.repository.EnchereRepository;
import com.example.gestionEncheres.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilisateurService {
    @Autowired(required=true)
    UtilisateurRepository utilisateurRepository;
    @Autowired(required=true)
    EnchereRepository enchereRepository;
    @Autowired(required=true)
    TokenService tokenService;

    //getting all utilisateurs record by using the method findaAll() of CrudRepository
    public List<Utilisateur> getAllUtilisateurs()
    {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        utilisateurRepository.findAll().forEach(utilisateurs::add);
        return utilisateurs;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Utilisateur getUtilisateursById(int id)
    {
        return utilisateurRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Utilisateur utilisateur) throws Exception {
        if (utilisateur.getNom() == null || utilisateur.getPrenoms() == null || utilisateur.getGenre() == null || utilisateur.getDateNaissance() == null || utilisateur.getEmail() == null || utilisateur.getPassword() == null)
            throw new Exception("Veuillez remplir tout les champs");
        else {
            for (Utilisateur user : this.getAllUtilisateurs()) {
                if (utilisateur.getEmail().equals(user.getEmail())) {
                    throw new Exception("Utilisateur existant");
                }
            }
            utilisateur.setPassword(DigestUtils.md5DigestAsHex(utilisateur.getPassword().getBytes(StandardCharsets.UTF_8)));
            utilisateurRepository.save(utilisateur);
        }
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        utilisateurRepository.deleteById(id);
    }
    //updating a record
    public void update(Utilisateur utilisateur, int bookid)
    {
        utilisateurRepository.save(utilisateur);
    }

    public Data login(Utilisateur user) throws Exception{
        if (user.getEmail() == null || user.getPassword() == null)
            throw new Exception("Veuillez remplir tout les champs");
        else {
            Utilisateur use = utilisateurRepository.findByEmailAndPassword(user.getEmail(), DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            if (use != null) {
                Token token = new Token();
                token.setToken(TokenService.generateToken(String.valueOf(use.getIdUtilisateur())));
                token.setUser(use);
                token.setDateGeneration(Timestamp.from(Instant.now()));
                tokenService.saveOrUpdate(token);
                return new Data(tokenService.getTokenByToken(token.getToken()));
            } else {
                throw new Exception("Identifiants incorrect");
            }
        }
    }

    public List<Enchere> getMyBids(int id) {
        return enchereRepository.getMyBids(id);
    }
}
