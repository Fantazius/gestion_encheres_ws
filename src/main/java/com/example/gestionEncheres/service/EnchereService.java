package com.example.gestionEncheres.service;

import com.example.gestionEncheres.database.DatabaseConnection;
import com.example.gestionEncheres.models.*;
import com.example.gestionEncheres.repository.EnchereDureeRepository;
import com.example.gestionEncheres.repository.EnchereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnchereService {
    @Autowired
    EnchereRepository encheresRepository;
    @Autowired
    EnchereDureeRepository enchereDureeRepository;

    //getting all encheres record by using the method findaAll() of CrudRepository
    public List<Enchere> getAllEncheres()
    {
        List<Enchere> encheres = new ArrayList<Enchere>();
        encheresRepository.findAll().forEach(encheres::add);
        return encheres;
    }
    //getting a specific record by using the method findById() of CrudRepository
    public Enchere getEncheresById(int id)
    {
        return encheresRepository.findById(id).get();
    }

    public boolean isInIntervalleImposed(Integer dureeEnchere){
        EnchereDuree enchereDuree = enchereDureeRepository.getLastEnchereDuree();
        int dureeMin = (int) enchereDuree.getDureeMin();
        int dureeMax = (int) enchereDuree.getDureeMax();
        if(dureeEnchere>=dureeMin && dureeEnchere<=dureeMax){
            return true;
        }
        else {
            return false;
        }
    }

    //saving a specific record by using the method save() of CrudRepository
    public void addEnchere(Enchere enchere) throws Exception {
        if(enchere.getPrix_min_enchere()>0){
            if(isInIntervalleImposed(enchere.getDuree())) {
                encheresRepository.addEnchere(enchere.getUtilisateur().getIdUtilisateur(), enchere.getProduit().getIdProduit(), enchere.getDescription(), enchere.getPrix_min_enchere(), enchere.getDuree());
            }
            else {
                throw new Exception("la duree n'est pas dans l'intervalle impose");
            }
        }
        else{
            throw new Exception("prix negatif");
        }
    }

    public List<Enchere> rechercher(String motCle, Date daty, int prix, boolean status, int categorie) throws Exception{
        List<Enchere> le = new ArrayList<>();
        // a modifier: user, mdp
        Connection con=new DatabaseConnection().toCo("administrateur","12345");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String condition = "select * from enchere_globale where ";

        if(motCle!=null){
            condition = condition + "description like '%"+motCle+"%'";
            if(daty!=null){
                condition = condition + " and dateenchere::date='"+daty+"'";
            }
            if(prix!=0){
                condition = condition + " and prix_min_enchere ="+prix;
            }
            if(status==true || status==false){
                condition = condition + " and statut='"+status+"'";
            }
            if(categorie!=0){
                condition = condition + " and idcategorie="+categorie;
            }
        }
        else if(daty!=null){
            condition = condition + "dateenchere::date='"+daty+"'";
            if(prix!=0){
                condition = condition + " and prix_min_enchere ="+prix;
            }
            if(status==true || status==false){
                condition = condition + " and statut='"+status+"'";
            }
            if(categorie!=0){
                condition = condition + " and idcategorie="+categorie;
            }
        }
        else if(prix!=0){
            condition = condition + "prix_min_enchere ="+prix;
            if(status==true || status==false){
                condition = condition + " and statut='"+status+"'";
            }
            if(categorie!=0){
                condition = condition + " and idcategorie="+categorie;
            }
        }
        else if(status==true || status==false){
            condition = condition + "statut='"+status+"'";
            if(categorie!=0){
                condition = condition + " and idcategorie="+categorie;
            }
        }
        else if(categorie!=0){
            condition = condition + "idcategorie="+categorie;
        }

        try{
            stmt=con.prepareStatement(condition);
            ResultSet data = stmt.executeQuery();
            while(data.next()){
                Categorie c = new Categorie();
                c.setIdCategorie(data.getInt("idcategorie"));
                c.setLibelle(data.getString("libelle"));
                Produit p = new Produit();
                p.setCategorie(c);
                p.setIdProduit(data.getInt("idproduit"));
                p.setNomProduit("nomproduit");
                Genre g = new Genre();
                g.setIdGenre(data.getInt("idgenre"));
                g.setGenre(data.getString("genre"));
                Utilisateur u = new Utilisateur();
                u.setIdUtilisateur(data.getInt("idutilisateur"));
                u.setNom(data.getString("nom"));
                u.setPrenoms((data.getString("prenoms")));
                u.setDateNaissance(data.getDate("datenaissance"));
                u.setEmail(data.getString("email"));
                u.setPassword(data.getString("password"));
                u.setMontantSolde(data.getInt("montantsolde"));
                u.setGenre(g);
                Enchere e = new Enchere();
                e.setIdEnchere(data.getInt("idenchere"));
                e.setDescription(data.getString("description"));
                e.setPrix_min_enchere(data.getInt("prix_min_enchere"));
                e.setDuree(data.getInt("duree"));
                e.setDateenchere(data.getTimestamp("dateenchere"));
                e.setStatut(data.getBoolean("statut"));
                e.setUtilisateur(u);
                e.setProduit(p);
                le.add(e);
            }
        }catch(SQLException e){
            e.getMessage();
        }
        finally{
            // close stmt
            // close Resusltset
            con.close();
            stmt.close();
        }
        return le;
        //return encheresRepository.rechercher(condition);
    }

    //deleting a specific record by using the method deleteById() of CrudRepository
    public void delete(int id)
    {
        encheresRepository.deleteById(id);
    }
    //updating a record
    public void update(Enchere enchere, int bookid)
    {
        encheresRepository.save(enchere);
    }
}
