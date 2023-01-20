package com.example.gestionEncheres.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name="commisions")
@NoArgsConstructor
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idcommission", nullable = false)
    private int idCommission;
    @Column(name = "pourcentage")
    private double pourcentage;
    @Column(name = "datechangement")
    private Date dateChangement;

    public int getIdCommission() {
        return idCommission;
    }

    public void setIdCommission(int idCommission) {
        this.idCommission = idCommission;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Date getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Date dateChangement) {
        this.dateChangement = dateChangement;
    }
}
