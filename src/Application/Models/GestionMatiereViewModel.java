package Application.Models;

public class GestionMatiereViewModel {
    private Integer refMatiere;
    private String LibelleMatiere;
    private Integer coeffMatiere;

    public Integer getRefMatiere() {
        return refMatiere;
    }

    public void setRefMatiere(Integer refMatiere) {
        this.refMatiere = refMatiere;
    }

    public String getLibelleMatiere() {
        return LibelleMatiere;
    }

    public void setLibelleMatiere(String libelleMatiere) {
        LibelleMatiere = libelleMatiere;
    }

    public Integer getCoeffMatiere() {
        return coeffMatiere;
    }

    public void setCoeffMatiere(Integer coeffMatiere) {
        this.coeffMatiere = coeffMatiere;
    }

    public GestionMatiereViewModel(Integer refMatiere, String libelleMatiere, Integer coeffMatiere) {
        this.refMatiere = refMatiere;
        LibelleMatiere = libelleMatiere;
        this.coeffMatiere = coeffMatiere;
    }
}
