package com.example.gestionEncheres.models;

public class MiseToken {
    private int idEnchere;
    private int montant;
    private String token;

    public MiseToken() {
    }


    public int getIdEnchere() {
        return idEnchere;
    }

    public void setIdEnchere(int idEnchere) {
        this.idEnchere = idEnchere;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "MiseToken{" +
                ", idEnchere=" + idEnchere +
                ", montant=" + montant +
                ", token='" + token + '\'' +
                '}';
    }
}
