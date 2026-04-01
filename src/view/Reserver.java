package view;

import java.sql.SQLException;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import model.Model;

public class Reserver {

    private JFrame frmProfil;
    private JTextField txtCarte;
    private JTextField txtISBN;
    private JButton btnReserver;
    private JButton btnOkISBN;
    private JLabel lblMessage;

    public Reserver() throws ClassNotFoundException, SQLException {
        initialize();
        frmProfil.setVisible(true);
    }

    private void initialize() {

        frmProfil = new JFrame();
        frmProfil.setTitle("Reserver");
        frmProfil.setBounds(100, 100, 633, 398);
        frmProfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmProfil.getContentPane().setLayout(null);

        JLabel lblTitre = new JLabel("Reserver un livre");
        lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitre.setBounds(176, 11, 138, 42);
        frmProfil.getContentPane().add(lblTitre);

        JLabel lblCarte = new JLabel("n° adherent");
        lblCarte.setBounds(10, 68, 99, 20);
        frmProfil.getContentPane().add(lblCarte);

        JLabel lblNom = new JLabel("ISBN");
        lblNom.setBounds(34, 109, 46, 14);
        frmProfil.getContentPane().add(lblNom);

        txtCarte = new JTextField();
        txtCarte.setBounds(119, 65, 195, 20);
        frmProfil.getContentPane().add(txtCarte);

        txtISBN = new JTextField();
        txtISBN.setBounds(119, 106, 195, 20);
        txtISBN.setEnabled(false);
        frmProfil.getContentPane().add(txtISBN);

        btnReserver = new JButton("RESERVER");
        btnReserver.setBounds(136, 175, 89, 23);
        btnReserver.setEnabled(false);
        frmProfil.getContentPane().add(btnReserver);

        btnOkISBN = new JButton("OK");
        btnOkISBN.setBounds(335, 105, 89, 23);
        btnOkISBN.setEnabled(false);
        frmProfil.getContentPane().add(btnOkISBN);
        
        lblMessage = new JLabel("");
        lblMessage.setBounds(119, 220, 300, 20);
        frmProfil.getContentPane().add(lblMessage);

        JButton btnOkNum = new JButton("Ok");
        btnOkNum.setBounds(335, 64, 89, 23);
        frmProfil.getContentPane().add(btnOkNum);
        
  

        // ===== bouton adherent =====
        btnOkNum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String num = txtCarte.getText();

                try {
                    Model model = new Model();
                    boolean resultat = model.verifAdherent(num);

                    if (resultat) { // true
                        txtISBN.setEnabled(true);
                        btnReserver.setEnabled(true);
                        btnOkISBN.setEnabled(true);
                    } else { //false
                    	lblMessage.setText("Numéro invalide");
                    	lblMessage.setForeground(java.awt.Color.RED);
                        
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();// Affiche l'erreur complète dans la console
                }
            }
        });

        // ===== bouton ISBN =====
        btnOkISBN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String ISBN = txtISBN.getText();

                try {
                    Model model = new Model();

                    if (model.verifISBN(ISBN)) { //true
                        System.out.println("ISBN valide");
                    } else { // false
                    	lblMessage.setText("ISBN invalide");
                    	lblMessage.setForeground(java.awt.Color.RED);
                    	
                        
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();// Affiche l'erreur complète dans la console
                }
            }
        });

        // ===== bouton RESERVER =====
        btnReserver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String num = txtCarte.getText();
                String ISBN = txtISBN.getText();

                try {
                    Model model = new Model();

                    
					if (model.livreDisponible(ISBN)) { // true
                        model.reserver(num, ISBN);
                        lblMessage.setText("Livre bien emprunté");
                        lblMessage.setForeground(java.awt.Color.GREEN);
                    } else {
                        lblMessage.setText("Livre déjà emprunté");
                        lblMessage.setForeground(java.awt.Color.RED);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();// Affiche l'erreur complète dans la console
                    lblMessage.setText("Erreur SQL ");
                    lblMessage.setForeground(java.awt.Color.RED);
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();// Affiche l'erreur complète dans la console
                    
                }
            }
        });
    }
}