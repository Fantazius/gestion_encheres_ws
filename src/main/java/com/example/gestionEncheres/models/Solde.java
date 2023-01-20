package com.example.gestionEncheres.models;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="soldes")
@NoArgsConstructor
public class Solde {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idsolde", nullable = false)
    private int idSolde;
    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;
    @Column(name = "montant")
    private int montant;
    @Column(name = "datedepot")
    private Timestamp dateDepot;

    @Column(name = "valid")
    private int valid;

    public int getValid() {
        return valid;
    }

    public void setValid(int valid) {
        this.valid = valid;
    }

    public int getIdSolde() {
        return idSolde;
    }

    public void setIdSolde(int idSolde) {
        this.idSolde = idSolde;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Timestamp getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(Timestamp dateDepot) {
        this.dateDepot = dateDepot;
    }
}
