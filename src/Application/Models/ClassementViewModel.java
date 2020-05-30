package Application.Models;

public class ClassementViewModel {
    private String nom_complet_etudiant;
    private Double moyenne_generale;
    private int classement;

    public ClassementViewModel(String nom_complet_etudiant, Double moyenne_generale, int classement) {
        this.nom_complet_etudiant = nom_complet_etudiant;
        this.moyenne_generale = moyenne_generale;
        this.classement = classement;
    }

    public String getNom_complet_etudiant() {
        return nom_complet_etudiant;
    }

    public Double getMoyenne_generale() {
        return moyenne_generale;
    }

    public void setMoyenne_generale(Double moyenne_generale) {
        this.moyenne_generale = moyenne_generale;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }
}
