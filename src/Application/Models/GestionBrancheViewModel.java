package Application.Models;

public class GestionBrancheViewModel {
    private Integer refBranche;
    private String libelleBranche;
    private String detailBranche;

    public Integer getRefBranche() {
        return refBranche;
    }

    public void setRefBranche(Integer refBranche) {
        this.refBranche = refBranche;
    }

    public String getLibelleBranche() {
        return libelleBranche;
    }

    public void setLibelleBranche(String libelleBranche) {
        this.libelleBranche = libelleBranche;
    }

    public String getDetailBranche() {
        return detailBranche;
    }

    public void setDetailBranche(String detailBranche) {
        this.detailBranche = detailBranche;
    }


    public GestionBrancheViewModel(Integer refBranche, String libelleBranche, String detailBranche) {
        this.refBranche = refBranche;
        this.libelleBranche = libelleBranche;
        this.detailBranche = detailBranche;
    }
}
