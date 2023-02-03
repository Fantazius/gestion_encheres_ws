package com.example.gestionEncheres.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name="utilisateurs")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
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
    private float montantSolde;

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtilisateur=" + idUtilisateur +
                ", nom='" + nom + '\'' +
                ", prenoms='" + prenoms + '\'' +
                ", genre=" + genre +
                ", dateNaissance=" + dateNaissance +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", montantSolde=" + montantSolde +
                '}';
    }
}
