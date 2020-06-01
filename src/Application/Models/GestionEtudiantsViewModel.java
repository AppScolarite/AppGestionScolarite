package Application.Models;

import java.util.Date;

public class GestionEtudiantsViewModel {
    private String code_massar;
    private String nom;
    private String prenom;
    private Date date_inscription;
    private String email;
    private String telephone;
    private String a_deja_redouble;
    private String adresse;
    private String sexe;

    public GestionEtudiantsViewModel(String code_massar, String nom, String prenom, Date date_inscription, String email, String telephone, String a_deja_redouble, String adresse, String sexe) {
        this.code_massar = code_massar;
        this.nom = nom;
        this.prenom = prenom;
        this.date_inscription = date_inscription;
        this.email = email;
        this.telephone = telephone;
        this.a_deja_redouble = a_deja_redouble;
        this.adresse = adresse;
        this.sexe = sexe;
    }


    public String getCode_massar() {
        return code_massar;
    }

    public void setCode_massar(String code_massar) {
        this.code_massar = code_massar;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getA_deja_redouble() {
        return a_deja_redouble;
    }

    public void setA_deja_redouble(String a_deja_redouble) {
        this.a_deja_redouble = a_deja_redouble;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

}
