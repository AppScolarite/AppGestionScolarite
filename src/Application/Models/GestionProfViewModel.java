package Application.Models;

public class GestionProfViewModel {
    String nomCodeProf;
    String CIN;
    String nomComplet;
    String typeContrat;
    String Matiere;
    String Groupes;

    public String getNomCodeProf() {
        return nomCodeProf;
    }

    public void setNomCodeProf(String nomCodeProf) {
        this.nomCodeProf = nomCodeProf;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getMatiere() {
        return Matiere;
    }

    public void setMatiere(String matiere) {
        Matiere = matiere;
    }

    public String getGroupes() {
        return Groupes;
    }

    public void setGroupes(String groupes) {
        Groupes = groupes;
    }


    public GestionProfViewModel(String nomCodeProf, String CIN, String nomComplet, String typeContrat, String matiere, String groupes) {
        this.nomCodeProf = nomCodeProf;
        this.CIN = CIN;
        this.nomComplet = nomComplet;
        this.typeContrat = typeContrat;
        Matiere = matiere;
        Groupes = groupes;
    }

}
