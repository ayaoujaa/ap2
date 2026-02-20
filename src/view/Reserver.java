package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.Main;
import model.Livre;
import model.Model;

public class Reserver {

    private JFrame frmProfil;
    private JTextField txtCarte;
    private JTextField txtISBN;
    private JButton btnReserver;
    private JButton btnOkISBN;

    public Reserver() throws ClassNotFoundException, SQLException {
        initialize();
        frmProfil.setVisible(true);
    }

    private void initialize() {

        // ===== JFrame =====
        frmProfil = new JFrame();
        frmProfil.setTitle("Profil");
        frmProfil.setBounds(100, 100, 633, 398);
        frmProfil.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmProfil.getContentPane().setLayout(null);

        // ===== Labels =====
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

        // ===== TextFields =====
        txtCarte = new JTextField();
        txtCarte.setBounds(119, 65, 195, 20);
        txtCarte.setColumns(10);
        frmProfil.getContentPane().add(txtCarte);

        txtISBN = new JTextField();
        txtISBN.setBounds(119, 106, 195, 20);
        txtISBN.setColumns(10);
        txtISBN.setEnabled(false);
        frmProfil.getContentPane().add(txtISBN);

        txtISBN.setEnabled(false);
        
        
     // ===== Bouton RESERVER =====
        btnReserver = new JButton("RESERVER");  // <-- plus de "JButton" devant !
        btnReserver.setBounds(136, 175, 89, 23);
        btnReserver.setEnabled(false);
        frmProfil.getContentPane().add(btnReserver);

        // ===== Bouton OK ISBN =====
        btnOkISBN = new JButton("OK");  // <-- plus de "JButton" devant !
        btnOkISBN.setBounds(335, 105, 89, 23);
        btnOkISBN.setEnabled(false);
        frmProfil.getContentPane().add(btnOkISBN);

        // ===== Bouton OK (adherent) =====
        JButton btnOkNum = new JButton("Ok");
        btnOkNum.setBounds(335, 64, 89, 23);
        frmProfil.getContentPane().add(btnOkNum);

        btnOkNum.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String num = txtCarte.getText();

                try {
                    Model model = new Model();
                    boolean resultat = model.verifAdherent(num);
                    

                    if (resultat) {
                        System.out.println("Livre déjà emprunté");
                        txtISBN.setEnabled(true);
                        btnReserver.setEnabled(true);
                        btnOkISBN.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Numéro invalide");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // ===== Bouton OK ISBN =====
        JButton btnOkISBN = new JButton("OK");
        btnOkISBN.setBounds(335, 105, 89, 23);
        btnOkISBN.setEnabled(false);
        //btnOkISBN.setEnabled(true);
        frmProfil.getContentPane().add(btnOkISBN);

        btnOkISBN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String num = txtCarte.getText();
                String ISBN = txtISBN.getText();
                
                
                
                try {
                    Model model = new Model();
                    ResultSet resultat = model.res(num, ISBN);

                    if (resultat.next()) {
                        System.out.println(" Numéro valide ");
                        txtISBN.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Numéro invalide");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        // ===== Bouton RESERVER =====
        JButton btnReserver = new JButton("RESERVER");
        btnReserver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		String num = txtCarte.getText();
        		String ISBN = txtISBN.getText();
        		
        		
        		try {
        			Model model = new Model();
					model.reserver(num, ISBN);
					//2 erreurs possible a gerer bdd
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		
        	}
        });
        btnReserver.setBounds(136, 175, 89, 23);
        btnReserver.setEnabled(false);
        frmProfil.getContentPane().add(btnReserver);
    }
}
