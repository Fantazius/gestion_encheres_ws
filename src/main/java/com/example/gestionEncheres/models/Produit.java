package com.example.gestionEncheres.models;
import javax.persistence.*;

import lombok.NoArgsConstructor;


@Entity
@Table(name="produits")
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idproduit", nullable = false)
    private int idProduit;
    @Column(name = "nomproduit")
    private String nomProduit;
    @ManyToOne(targetEntity = Categorie.class)
    @JoinColumn(name = "idcategorie", referencedColumnName = "idcategorie")
    private Categorie categorie;

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
