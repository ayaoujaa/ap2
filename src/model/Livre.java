package model;

public class Livre {
    private String ISBN;
    private String titre;
    private float prix;
    private Adherent emprunteur;
    private Auteur auteur;
    
    public Livre(String iSBN, String titre, float prix, Adherent emprunteur, Auteur auteur) {
        ISBN = iSBN;
        this.titre = titre;
        this.prix = prix;
        this.emprunteur = emprunteur;
        this.auteur = auteur;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Adherent getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Adherent emprunteur) {
        this.emprunteur = emprunteur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }
}