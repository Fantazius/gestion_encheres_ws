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

    public Connection toCo()throws Exception
    {
        Class.forName("org.postgresql.Driver"); // PostGres
        Connection con= DriverManager.getConnection("jdbc:postgres://zwctziwf:EbK07yQmesBqCasSqaqQXgecBTX4stH1@surus.db.elephantsql.com/zwctziwf"); // PostGres
        return con;
    }
}
