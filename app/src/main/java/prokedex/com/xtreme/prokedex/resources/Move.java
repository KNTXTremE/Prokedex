package prokedex.com.xtreme.prokedex.resources;

public class Move {
    private String name;
    private int type;
    private int category;
    private String power;
    private String accuracy;

    public Move(String name, int type, int category, String power, String accuracy){
        this.name = name;
        this.type = type;
        this.category = category;
        this.power = power;
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public int getCategory() {
        return category;
    }

    public String getPower() {
        return power;
    }

    public String getAccuracy() {
        return accuracy;
    }
}
