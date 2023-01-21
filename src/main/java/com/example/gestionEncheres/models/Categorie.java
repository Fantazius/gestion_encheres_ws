package com.example.gestionEncheres.models;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="categories")
@NoArgsConstructor
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcategorie", nullable = false)
    private int idCategorie;
    @Column(name = "libelle")
    private String libelle;

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
