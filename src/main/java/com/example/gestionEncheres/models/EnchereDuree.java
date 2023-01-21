package com.example.gestionEncheres.models;

import java.sql.Timestamp;
import javax.persistence.*;

import lombok.NoArgsConstructor;

@Entity
@Table(name="encheredurees")
@NoArgsConstructor
public class EnchereDuree {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idduree", nullable = false)
    private int idDuree;
    @Column(name = "dureemin")
    private double dureeMin;
    @Column(name = "dureemax")
    private double dureeMax;
    @Column(name = "datechangement")
    private Timestamp dateChangement;

    public int getIdDuree() {
        return idDuree;
    }

    public void setIdDuree(int idDuree) {
        this.idDuree = idDuree;
    }

    public double getDureeMin() {
        return dureeMin;
    }

    public void setDureeMin(double dureeMin) {
        this.dureeMin = dureeMin;
    }

    public double getDureeMax() {
        return dureeMax;
    }

    public void setDureeMax(double dureeMax) {
        this.dureeMax = dureeMax;
    }

    public Timestamp getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Timestamp dateChangement) {
        this.dateChangement = dateChangement;
    }
}
