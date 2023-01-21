package com.example.gestionEncheres.models;

import javax.persistence.*;

import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name="admins")
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadmin", nullable = false)
    private int idAdmin;
    @Column(name = "nom")
    private String nom;
    @Column(name = "prenoms")
    private String prenoms;
    //@Column(name = "idgenre")
    //private int idGenre;
    @ManyToOne(targetEntity = Genre.class)
    @JoinColumn(name = "idgenre", referencedColumnName = "idgenre")
    private Genre genre;
    @Column(name = "datenaissance")
    private Date dateNaissance;
    @Column(name = "identifiant")
    private String identifiant;
    @Column(name = "password")
    private String password;

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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
    /*
    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    */

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

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
