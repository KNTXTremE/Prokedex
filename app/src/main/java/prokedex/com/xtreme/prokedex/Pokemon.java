package prokedex.com.xtreme.prokedex;

/**
 * Created by KanTLovE on 11/13/2017.
 */

public class Pokemon {
    protected String id;
    protected String name;
    protected String nameJap;
    protected int resId;
    protected int element1;
    protected int element2;
    protected boolean isCaught;

    public Pokemon(String id, String name, String nameJap, int resId, int element1, int element2){
        this.id = id;
        this.name = name;
        this.nameJap = nameJap;
        this.resId = resId;
        this.element1 = element1;
        this.element2 = element2;
        this.isCaught = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameJap() {
        return nameJap;
    }

    public int getResId() {
        return resId;
    }

    public int getElement1() {
        return element1;
    }

    public int getElement2() {
        return element2;
    }

    public boolean isCaught() {
        return isCaught;
    }

    public void setCaught(boolean caught) {
        this.isCaught = caught;
    }

}
