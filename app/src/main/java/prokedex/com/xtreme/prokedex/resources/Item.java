package prokedex.com.xtreme.prokedex.resources;

public class Item {
    private int image;
    private String name;
    private String description;

    public Item(int image, String name, String description){
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
