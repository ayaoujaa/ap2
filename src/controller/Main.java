package controller;

import java.sql.*;

import model.Model;
import view.Accueil;


public class Main {

    private static Model m;

    public static Model getM() {
        return m;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        m = new Model();
        m.getAll();
        //new View_Connexion();
        new Accueil();
    }
}