package com.example.gestionEncheres.models;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="gagnants")
@NoArgsConstructor
public class Gagnant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idenchere", nullable = false)
    private int idEnchere;
    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;
    @Column(name = "montant")
    private int montant;

    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
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
}
