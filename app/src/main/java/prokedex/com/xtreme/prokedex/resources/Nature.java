package prokedex.com.xtreme.prokedex.resources;

public class Nature {
    private String name;
    private String incStat;
    private String decStat;
    private String favFlavor;
    private String disFlavor;

    public Nature(String name, String incStat, String decStat, String favFlavor, String disFlavor){
        this.name = name;
        this.incStat = incStat;
        this.decStat = decStat;
        this.favFlavor = favFlavor;
        this.disFlavor = disFlavor;
    }

    public String getName() {
        return name;
    }

    public String getIncStat() {
        return incStat;
    }

    public String getDecStat() {
        return decStat;
    }

    public String getFavFlavor() {
        return favFlavor;
    }

    public String getDisFlavor() {
        return disFlavor;
    }
}
