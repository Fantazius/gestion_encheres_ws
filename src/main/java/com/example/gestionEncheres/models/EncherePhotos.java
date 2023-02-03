package com.example.gestionEncheres.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class EncherePhotos extends Enchere{
    private String[] images;

    public Enchere toEnchere(){
        return new Enchere(getIdEnchere(),getUtilisateur(),getProduit(),getDescription(),getPrix_min_enchere(),getDuree(),getDateenchere(),false);
    }

    public Photo toPhoto(){
        return new Photo(getIdEnchere(),getImages());
    }

    public EncherePhotos(Enchere enchere)throws Exception{
        this.setIdEnchere(enchere.getIdEnchere());
        this.setUtilisateur(enchere.getUtilisateur());
        this.setProduit(enchere.getProduit());
        this.setDescription(enchere.getDescription());
        this.setPrix_min_enchere(enchere.getPrix_min_enchere());
        this.setDuree(enchere.getDuree());
        this.setDateenchere(enchere.getDateenchere());
        this.setStatut(enchere.isStatut());
    }
    @Override
    public String toString() {
        return "EncherePhotos{" +
                "images=" + Arrays.toString(images) +
                "} " + super.toString();
    }
}
