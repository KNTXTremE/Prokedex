package prokedex.com.xtreme.prokedex;

/**
 * Created by KanTLovE on 11/13/2017.
 */

public class Pokemon {
    protected String name;
    protected String nameJap;
    protected int resId;
    protected String element1;
    protected String element2;

    public Pokemon(String name, String nameJap, int resId, String element1, String element2){
        this.name = name;
        this.nameJap = nameJap;
        this.resId = resId;
        this.element1 = element1;
        this.element2 = element2;
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

    public void setNameJap(String nameJap) {
        this.nameJap = nameJap;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getElement1() {
        return element1;
    }

    public void setElement1(String element1) {
        this.element1 = element1;
    }

    public String getElement2() {
        return element2;
    }

    public void setElement2(String element2) {
        this.element2 = element2;
    }

}
