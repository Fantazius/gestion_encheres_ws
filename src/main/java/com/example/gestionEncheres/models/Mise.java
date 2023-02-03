package com.example.gestionEncheres.models;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="mises")
@NoArgsConstructor
public class Mise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "idmise", nullable = false)
    private int idMise;
    @ManyToOne(targetEntity = Utilisateur.class)
    @JoinColumn(name = "idutilisateur", referencedColumnName = "idutilisateur")
    private Utilisateur utilisateur;
    @ManyToOne(targetEntity = Enchere.class)
    @JoinColumn(name = "idenchere", referencedColumnName = "idenchere")
    private Enchere enchere;
    @Column(name = "montant")
    private int montant;
    @Column(name = "datemise")
    private Timestamp dateMise;

    public int getIdMise() {
        return idMise;
    }

    public void setIdMise(int idMise) {
        this.idMise = idMise;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public Timestamp getDateMise() {
        return dateMise;
    }

    public void setDateMise(Timestamp dateMise) {
        this.dateMise = dateMise;
    }

    public Mise(int idutilisateur, int idenchere, int montant) {
        this.utilisateur = new Utilisateur();
        this.utilisateur.setIdUtilisateur(idutilisateur);
        this.enchere = new Enchere();
        this.enchere.setIdEnchere(idenchere);
        this.montant = montant;
    }

    public Gagnant toGagnant(){
        Gagnant g=new Gagnant();
        System.out.println(this);
        System.out.println(this.getEnchere().getIdEnchere());
        g.setIdEnchere(this.getEnchere().getIdEnchere());
        g.setMontant(this.montant);
        g.setUtilisateur(this.getUtilisateur());
        return g;
    }

    @Override
    public String toString() {
        return "Mise{" +
                "idMise=" + idMise +
                ", utilisateur=" + utilisateur +
                ", enchere=" + enchere +
                ", montant=" + montant +
                ", dateMise=" + dateMise +
                '}';
    }
}
