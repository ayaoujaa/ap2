package model;

import java.sql.*;
import java.util.ArrayList;

public class Model {

    private static Connection con;
    private ArrayList<Livre> ListLivre;
    private ArrayList<Auteur> ListAuteur;

    public Model() throws ClassNotFoundException, SQLException {
        String BDD = "ap2sql";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "root";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, user, passwd);
        System.out.println("Connexion réussie !");
    }
    
    
    
    
    
    
    // CATALOGUE
    
    public ResultSet getAllLivres() throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "SELECT livre.ISBN, livre.titre, auteur.nom, auteur.prenom, livre.prix " +
                     "FROM livre LEFT JOIN auteur ON livre.auteur = auteur.num";
        ResultSet listeLivre = stmt.executeQuery(sql);
        return listeLivre;
    }
    
    
    
    
    // verification du num adherent
    
    
    public boolean verifAdherent(String num) throws SQLException {
        Statement stmt = con.createStatement();
        String sql = "SELECT num FROM adherent WHERE num = '" + num + "'";
        ResultSet rs = stmt.executeQuery(sql);

        // retourne true si le numéro existe, false sinon
        return rs.next();
    }

    // PROFIL MAJ
    
    public void majAdherent(String nom, String prenom , String email, String num) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE adherent SET nom = '" + nom + "', prenom = '" + prenom + "', email = '" + email + "' WHERE num = '" + num + "'";
    	stmt.executeUpdate(sql);
        System.out.println("Produit modifié avec succès !");
	
    }
    
    
    
    // AJOUTER ADHERENT
    
    public static void ajoutAd(String nom, String prenom , String email) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "INSERT INTO adherent (nom, prenom, email ) VALUES ('"+ nom + "', '" + prenom + "', " 
        		 + email + ")";
    	stmt.executeUpdate(sql);
    }
    
    
    
    
    
    // RESERVATION
    // verif si isbn existe
    public ResultSet res(String num ,String ISBN) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sqlverif = "SELECT adherent FROM livre WHERE ISBN = '" + ISBN + "'";
    	ResultSet resultatRes = stmt.executeQuery(sqlverif);
    	return resultatRes;
	
    }
    
    // MISE A JOUR BDD EN AJT LE NUM ADHERENT A L'ISBN
    public void reserver(String num,String ISBN) throws SQLException {
    	Statement stmt = con.createStatement();
    	String sql = "UPDATE livre SET adherent ='"+ num +"'WHERE ISBN ='"+ISBN +"'";
		stmt.executeUpdate(sql);
		System.out.println("Livre emprunté avec succès !");
    }

    
    
    
    
    
    
    
    // ===== Récupération des livres et auteurs =====
    public void getAll() throws SQLException {

        ListLivre = new ArrayList<>();
        ListAuteur = new ArrayList<>();

        // Un seul Statement pour tout
        Statement stmt = con.createStatement();

        // ===== Auteurs =====
        ResultSet rs = stmt.executeQuery("SELECT * FROM auteur");
        while (rs.next()) {
            String num = rs.getString("num");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            String date_naissance = rs.getString("date_naissance");
            String description = rs.getString("description");

            Auteur auteur = new Auteur(num, nom, prenom, date_naissance, description);
            ListAuteur.add(auteur);
        }

        // ===== Livres =====
        rs = stmt.executeQuery("SELECT * FROM livre");
        while (rs.next()) {
            String ISBN = rs.getString("ISBN");
            String titre = rs.getString("titre");
            float prix = rs.getFloat("prix");
            String numAuteur = rs.getString("auteur");

            Livre livre = new Livre(ISBN, titre, prix, null, null);

            // Lier l'auteur si existant
            if (numAuteur != null) {
                livre.setAuteur(findAuteur(numAuteur));
            }

            ListLivre.add(livre);
        }
    }

    // ===== Recherche auteur par numéro =====
    private Auteur findAuteur(String num) {
        for (Auteur a : ListAuteur) {
            if (a.getNum().equals(num)) return a;
        }
        return null;
    }

    // ===== GETTERS =====
    public ArrayList<Livre> getListLivre() {
        return ListLivre;
    }

    public ArrayList<Auteur> getListAuteur() {
        return ListAuteur;
    }
}


	
