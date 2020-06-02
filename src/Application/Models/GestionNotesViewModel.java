package Application.Models;

public class GestionNotesViewModel {
    private String code_massar;
    private String nom_complet;
    private String control_1;
    private String control_2;
    private String control_3;
    private String moyenne;

    public GestionNotesViewModel(String code_massar, String nom_complet, String control_1, String control_2, String control_3, String moyenne) {
        this.code_massar = code_massar;
        this.nom_complet = nom_complet;
        this.control_1 = control_1;
        this.control_2 = control_2;
        this.control_3 = control_3;
        this.moyenne = moyenne;
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

    public String getControl_1() {
        return control_1;
    }

    public void setControl_1(String control_1) {
        this.control_1 = control_1;
    }

    public String getControl_2() {
        return control_2;
    }

    public void setControl_2(String control_2) {
        this.control_2 = control_2;
    }

    public String getControl_3() {
        return control_3;
    }

    public void setControl_3(String control_3) {
        this.control_3 = control_3;
    }

    public String getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }
}