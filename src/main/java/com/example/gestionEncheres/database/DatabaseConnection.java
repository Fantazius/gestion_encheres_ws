package com.example.gestionEncheres.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private String user;
    private String pwd;
    private String quest;

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }

    public String getQuest() {
        return quest;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public DatabaseConnection() {
    }

    public DatabaseConnection(String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public Connection toCo(String user, String pwd)throws Exception
    {
        Class.forName("org.postgresql.Driver"); // PostGres
        Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/gestion_encheres",user,pwd); // PostGres
        return con;
    }
}
