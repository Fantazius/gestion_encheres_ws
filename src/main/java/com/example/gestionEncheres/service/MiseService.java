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

    //getting all mises record by using the method findaAll() of CrudRepository
    public List<Mise> getAllMises()
    {
        List<Mise> mises = new ArrayList<Mise>();
        miseRepository.findAll().forEach(mises::add);
        return mises;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Mise getMisesById(int id)
    {
        return miseRepository.findById(id).get();
    }
    //saving a specific record by using the method save() of CrudRepository
    public void saveOrUpdate(Mise mise)
    {
        miseRepository.save(mise);
    }
    //deleting a specific record by using the method deleteById() of CrudRepository
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
                //insert sql;
//                Mise mise=new Mise(t.getIdUser(),idEnchere,montant);
                return miseRepository.addMise(t.getIdUser(),idEnchere,montant);
            }
            else{
                throw new Exception("Not able to bid");
            }
        }
        else{
            throw new Exception("Must be logged");
        }
    }
}
