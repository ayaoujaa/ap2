package view;

import java.sql.SQLException;
import javax.swing.*;
import model.Model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil {

    private JFrame frame;

    public Accueil() throws ClassNotFoundException, SQLException {
        initialize();
        frame.setVisible(true);
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Accueil");
        frame.setBounds(100, 100, 400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel titreLabel = new JLabel("ACCUEIL");
        titreLabel.setBounds(100, 20, 200, 30);
        titreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(titreLabel);

        JButton btnLivres = new JButton("Liste des livres");
        btnLivres.setBounds(50, 70, 300, 40);
        frame.getContentPane().add(btnLivres);
        
        
        
        // Listener pour ouvrir le Catalogue
        btnLivres.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Model model = new Model();  // crée le modèle
                    model.getAll();             // récupère les livres
                    new Catalogue(model);       // ouvre la page Catalogue
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });




        JButton btnEmprunter = new JButton("Reserver un livre");
        btnEmprunter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new Reserver();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnEmprunter.setBounds(50, 120, 300, 40);
        frame.getContentPane().add(btnEmprunter);

        JButton btnCompte = new JButton("Creation adherent");
        btnCompte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new CreationAd();
        	}
        });
        btnCompte.setBounds(50, 170, 300, 40);
        frame.getContentPane().add(btnCompte);
        
        JButton btnProfil = new JButton("Modifier le profil");
        btnProfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					new Profil();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        btnProfil.setBounds(50, 221, 300, 40);
        frame.getContentPane().add(btnProfil);

        

       

        
    }
}