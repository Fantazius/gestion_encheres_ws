package com.example.gestionEncheres.models;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "admintokens")
@NoArgsConstructor
public class AdminToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idadmintoken", nullable = false)
    private Integer idAdminToken;
    @Column(name = "idadmin",insertable = false,updatable = false)
    private Integer idAdmin;
    @Column(name = "token")
    private String token;
    @Column(name = "dategeneration")
    private Timestamp dateGeneration;
    @Column(name = "dateexpiration")
    private Timestamp dateExpiration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idadmin",nullable = false)
    private Admin admin;

    public Integer getIdAdminToken() {
        return idAdminToken;
    }

    public void setIdAdminToken(Integer idToken) {
        this.idAdminToken = idToken;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getDateGeneration() {
        return dateGeneration;
    }

    public void setDateGeneration(Timestamp dateGeneration) {
        this.dateGeneration = dateGeneration;
    }

    public Timestamp getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Timestamp dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
