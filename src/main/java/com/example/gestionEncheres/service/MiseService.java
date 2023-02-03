package com.example.gestionEncheres.service;

import com.example.gestionEncheres.models.Enchere;
import com.example.gestionEncheres.models.Mise;
import com.example.gestionEncheres.models.Token;
import com.example.gestionEncheres.repository.EnchereRepository;
import com.example.gestionEncheres.repository.MiseRepository;
import com.example.gestionEncheres.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MiseService {
    @Autowired(required=true)
    MiseRepository miseRepository;
    @Autowired(required=true)
    TokenRepository tokenRepository;
    @Autowired(required=true)
    EnchereRepository enchereRepository;
    @Autowired(required=true)
    EnchereService enchereService;



    public List<Mise> miseByIdenchere(int idenchere){
        List<Mise> mises = new ArrayList<>();
        miseRepository.miseByIdenchere(idenchere).forEach(mises::add);
        return mises;
    }

    //getting all mises record by using the method findaAll() of JpaRepository
    public List<Mise> getAllMises()
    {
        List<Mise> mises = new ArrayList<Mise>();
        miseRepository.findAll().forEach(mises::add);
        return mises;
    }
    //getting a specific record by using the method findById() of JpaRepository
    public Mise getMisesById(int id)
    {
        return miseRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of JpaRepository
    public void saveOrUpdate(Mise mise)
    {
        miseRepository.save(mise);
    }
    //deleting a specific record by using the method deleteById() of JpaRepository
    public void delete(int id)
    {
        miseRepository.deleteById(id);
    }
    //updating a record
    public void update(Mise mise, int bookid)
    {
        miseRepository.save(mise);
    }

    public boolean isValid(String token){
        return tokenRepository.isTokenValid(token);
    }

    public boolean isMine(Integer idUtilisateur, Integer idEnchere){
        System.out.println("MINE="+enchereRepository.isMine(idUtilisateur, idEnchere));
        if(enchereRepository.isMine(idUtilisateur, idEnchere)==null){
            return false;
        }
        return true;

    }

    @Transactional
    public int miser(String token, Integer idEnchere, int montant) throws Exception{
        System.out.println("TOKEN="+token);
        if(this.isValid(token)){
            Token t=tokenRepository.getTokenByToken(token);
            if(!this.isMine(t.getIdUser(), idEnchere)){
                Enchere e = enchereService.getEncheresById(idEnchere);
                if (montant < e.getPrix_min_enchere()) throw new Exception("Mise inferieure au prix de depart");
                if (montant <= miseRepository.getGagnant(idEnchere).getMontant()) throw new Exception("Mise inferieure à la mise gagnante");
                return miseRepository.addMise(t.getIdUser(),idEnchere,montant);
            }
            else{
                throw new Exception("Ne peux pas miser sur sa propre enchere");
            }
        }
        else{
            throw new Exception("Connexion requise");
        }
    }
}
