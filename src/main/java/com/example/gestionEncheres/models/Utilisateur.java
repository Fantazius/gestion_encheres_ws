package com.example.gestionEncheres.models;

import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="utilisateurs")
@NoArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idutilisateur", nullable = false)
    private int idUtilisateur;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenoms")
    private String prenoms;
    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "idgenre", referencedColumnName = "idgenre")
    private Genre genre;
    @Column(name = "datenaissance")
    private Date dateNaissance;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "montantsolde")
    private int montantSolde;


    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMontantSolde() {
        return montantSolde;
    }

    public void setMontantSolde(int montantSolde) {
        this.montantSolde = montantSolde;
    }
}
