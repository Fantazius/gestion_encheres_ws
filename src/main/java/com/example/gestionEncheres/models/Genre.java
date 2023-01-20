package com.example.gestionEncheres.models;

import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="genres")
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idgenre", nullable = false)
    private int idGenre;
    @Column(name = "genre")
    private String genre;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
