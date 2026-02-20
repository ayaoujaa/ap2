package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import controller.Main;
import model.Livre;
import model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Profil {

    private JFrame frmProfil;
    private JTextField txtCarte;
    private JTextField txtNom;
    private JTextField txtPrenom;
    private JTextField txtEmail;
    

    public Profil() throws ClassNotFoundException, SQLException {
        // mainMVC.getM().getAll(); // Tu peux décommenter si nécessaire et avoir mainMVC défini
        initialize();
        frmProfil.setVisible(true);
    }

    private void initialize() {
    	
    	
        frmProfil = new JFrame();
        frmProfil.setTitle("Profil");
        frmProfil.setBounds(100, 100, 633, 398);
        frmProfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmProfil.getContentPane().setLayout(null);

        txtCarte = new JTextField();
        txtCarte.setBounds(78, 65, 195, 20);
        frmProfil.getContentPane().add(txtCarte);
        txtCarte.setColumns(10);

        JLabel lblCarte = new JLabel("n°carte");
        lblCarte.setBounds(22, 68, 46, 14);
        frmProfil.getContentPane().add(lblCarte);

        JLabel lblTitre = new JLabel("PROFIL");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBounds(216, 22, 46, 14);
        frmProfil.getContentPane().add(lblTitre);

        JPanel panel = new JPanel();
        panel.setBounds(22, 153, 559, 195);
        frmProfil.getContentPane().add(panel);
        panel.setLayout(null);

        txtNom = new JTextField();
        txtNom.setEnabled(false);
        txtNom.setColumns(10);
        txtNom.setBounds(117, 11, 195, 20);
        panel.add(txtNom);

        txtPrenom = new JTextField();
        txtPrenom.setEnabled(false);
        txtPrenom.setColumns(10);
        txtPrenom.setBounds(117, 55, 195, 20);
        panel.add(txtPrenom);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(117, 96, 195, 20);
        panel.add(txtEmail);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setBounds(32, 14, 46, 14);
        panel.add(lblNom);

        JLabel lblPrenom = new JLabel("Prénom");
        lblPrenom.setBounds(32, 58, 46, 14);
        panel.add(lblPrenom);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(32, 99, 46, 14);
        panel.add(lblEmail);

        JButton btnMaj = new JButton("Maj");
        btnMaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Model model = null;

                try {
                    model = new Model(); // création du modèle
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }

                if (model != null) {

                    String email = txtEmail.getText();
                    String prenom = txtPrenom.getText();
                    String nom = txtNom.getText();
                    String num = txtCarte.getText();

                    try {
                        model.majAdherent(nom, prenom, email, num);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        
        btnMaj.setBounds(167, 142, 89, 23);
        panel.add(btnMaj);

        
        		
        txtNom.setEnabled(false);
        txtPrenom.setEnabled(false);
        txtEmail.setEnabled(false);
        btnMaj.setEnabled(false);
        
        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String num = txtCarte.getText();

                try {
                    Model model = new Model();
                    
                    // verifAdherent renvoie si le num existe
                    if (model.verifAdherent(num)) {
                        // numéro valide on débloque le bouton MAJ
                    	
                    	txtNom.setEnabled(true);
                        txtPrenom.setEnabled(true);
                        txtEmail.setEnabled(true);
                        btnMaj.setEnabled(true);

                        

                    } else {
                        JOptionPane.showMessageDialog(null, "Numéro invalide");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnOk.setBounds(335, 64, 89, 23);
        frmProfil.getContentPane().add(btnOk);
    }
}

 