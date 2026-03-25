package view;

import java.sql.SQLException;

import javax.swing.*;

import model.Model;

public class CreationAd {

    private JFrame frmAjtlivre;
    private JTextField textPrenom;
    private JTextField textEmail;
    private JTextField textNum;
    private JTextField textNom;


    public CreationAd() {
        initialize();
        frmAjtlivre.setVisible(true);
    }

    private void initialize() {
        frmAjtlivre = new JFrame();
        frmAjtlivre.setTitle("ajtAdherent");
        frmAjtlivre.setBounds(100, 100, 400, 300);
        frmAjtlivre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmAjtlivre.getContentPane().setLayout(null);

       
        
        textPrenom = new JTextField();
        textPrenom.setBounds(127, 103, 96, 20);
        frmAjtlivre.getContentPane().add(textPrenom);
        textPrenom.setColumns(10);
        
        JLabel lblNewLabel2 = new JLabel("Num :");
        lblNewLabel2.setBounds(43, 62, 49, 14);
        frmAjtlivre.getContentPane().add(lblNewLabel2);
        
        
        JLabel lblNewLabel = new JLabel("Prenom :");
        lblNewLabel.setBounds(43, 106, 49, 14);
        frmAjtlivre.getContentPane().add(lblNewLabel);
        
        JLabel lblPrix = new JLabel("Email :");
        lblPrix.setBounds(43, 187, 49, 17);
        frmAjtlivre.getContentPane().add(lblPrix);
        
        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(127, 185, 96, 20);
        frmAjtlivre.getContentPane().add(textEmail);
        
        textNum = new JTextField();
        textNum.setColumns(10);
        textNum.setBounds(127, 59, 96, 20);
        frmAjtlivre.getContentPane().add(textNum);
        
        JLabel lblAjoutDunLivre = new JLabel("Ajouter un adherent");
        lblAjoutDunLivre.setBounds(116, 23, 137, 14);
        frmAjtlivre.getContentPane().add(lblAjoutDunLivre);
        
        JButton valider = new JButton("Valider");
        valider.setBounds(257, 101, 80, 25);
        frmAjtlivre.getContentPane().add(valider);
        
        textNom = new JTextField();
        textNom.setColumns(10);
        textNom.setBounds(127, 146, 96, 20);
        frmAjtlivre.getContentPane().add(textNom);
        
        JLabel lblNewLabel2_1 = new JLabel("Nom :");
        lblNewLabel2_1.setBounds(43, 149, 49, 14);
        frmAjtlivre.getContentPane().add(lblNewLabel2_1);

        valider.addActionListener(e -> {
            try {
                // Récupération des valeurs au moment du clic
            	
            	String num = textNum.getText();
            	String nom = textNom.getText();
                String prenom = textPrenom.getText();
                String email = textEmail.getText();
                
                
                // Affichage test
                System.out.println("Num :" + num);
                System.out.println("Nom:" + nom);
                System.out.println("Prenom : " + prenom);
                System.out.println("Email: " + email);
                Model model = new Model(); // initialise la connexion
                model.ajoutAd(num,nom, prenom, email);
                

                JOptionPane.showMessageDialog(frmAjtlivre, "Adherent ajouté avec succès !");
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace(); // Affiche l'erreur complète dans la console
                JOptionPane.showMessageDialog(frmAjtlivre,
                    "Erreur SQL : " + ex.getMessage(), // Affiche juste le message SQL dans une popup
                    "Erreur SQL", JOptionPane.ERROR_MESSAGE);
            }
        });
	
        	
        }
}

