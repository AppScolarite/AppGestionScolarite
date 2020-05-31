package Application.Models;

import java.util.Date;

public class GestionEtudiantsViewModel {
    private String code_massar;
    private String nom_complet;
    private Date date_inscription;
    private String email;
    private String telephone;
    private String a_deja_redouble;
    private String adresse;
    private String sexe;
//    private Double moyenne;

    public GestionEtudiantsViewModel(String code_massar, String nom_complet, Date date_inscription, String email, String telephone, String a_deja_redouble, String adresse, String sexe) {
        this.code_massar = code_massar;
        this.nom_complet = nom_complet;
        this.date_inscription = date_inscription;
        this.email = email;
        this.telephone = telephone;
        this.a_deja_redouble = a_deja_redouble;
        this.adresse = adresse;
        this.sexe = sexe;
//        this.moyenne = moyenne;
    }


    public String getCode_massar() {
        return code_massar;
    }

    public void setCode_massar(String code_massar) {
        this.code_massar = code_massar;
    }

    public String getNom_complet() {
        return nom_complet;
    }

    public void setNom_complet(String nom_complet) {
        this.nom_complet = nom_complet;
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

//    public Double getMoyenne() {
//        return moyenne;
//    }
//
//    public void setMoyenne(Double moyenne) {
//        this.moyenne = moyenne;
//    }
}
