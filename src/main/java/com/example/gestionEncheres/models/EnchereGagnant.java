package com.example.gestionEncheres.models;


import javax.persistence.*;
import lombok.NoArgsConstructor;

import java.util.List;


@Table(name="encheresgagnant")
@NoArgsConstructor
@Entity
public class EnchereGagnant {

    @Id
    int idenchere;
    @Column(name = "nomgagnant")
    String nomgagnant;
    @Column(name = "idgagnant")
    int idgagnant;
    @Column(name = "idvendeur")
    int idvendeur;
    @Column(name = "nomvendeur")
    String nomvendeur;
    @Column(name = "idproduit")
    int idproduit;
    @Column(name = "nomproduit")
    String nomproduit;
    @Column(name = "montant")
    double montant;

    public int getIdenchere() {
        return idenchere;
    }

    public void setIdenchere(int idenchere) {
        this.idenchere = idenchere;
    }

    public String getNomgagnant() {
        return nomgagnant;
    }

    public void setNomgagnant(String nomgagnant) {
        this.nomgagnant = nomgagnant;
    }

    public int getIdgagnant() {
        return idgagnant;
    }

    public void setIdgagnant(int idgagnant) {
        this.idgagnant = idgagnant;
    }

    public int getIdvendeur() {
        return idvendeur;
    }

    public void setIdvendeur(int idvendeur) {
        this.idvendeur = idvendeur;
    }

    public String getNomvendeur() {
        return nomvendeur;
    }

    public void setNomvendeur(String nomvendeur) {
        this.nomvendeur = nomvendeur;
    }

    public int getIdproduit() {
        return idproduit;
    }

    public void setIdproduit(int idproduit) {
        this.idproduit = idproduit;
    }

    public String getNomproduit() {
        return nomproduit;
    }

    public void setNomproduit(String nomproduit) {
        this.nomproduit = nomproduit;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
