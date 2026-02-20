package view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import model.Model;

public class Catalogue {
    private JFrame frame;
    private JTextArea textArea;

    public Catalogue(Model model) throws SQLException {
        // Fenêtre
        frame = new JFrame("Catalogue des livres");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Titre centré
        JLabel titreLabel = new JLabel("Catalogue des livres", SwingConstants.CENTER);
        titreLabel.setFont(new Font("Arial", Font.BOLD, 28));
        frame.add(titreLabel, BorderLayout.NORTH);

        // Zone de texte pour afficher les livres
        textArea = new JTextArea();
        textArea.setEditable(false); // pas modifiable
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Récupération du ResultSet depuis le modèle
        ResultSet listeLivre = model.getAllLivres();

        // Tant qu'il y a une ligne, on ajoute dans le JTextArea
        while(listeLivre.next()) {
            String ligne = listeLivre.getString("ISBN") + " | " +
            		listeLivre.getString("titre") + " | " +
            		listeLivre.getString("nom") + " " + listeLivre.getString("prenom") + " | " +
            		listeLivre.getFloat("prix") + "€";
            textArea.append(ligne + "\n"); // retour a la ligne apres l'affichage de chaque ligne
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        Model model = new Model();
        new Catalogue(model);
    }
}

