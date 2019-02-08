package prokedex.com.xtreme.prokedex.PokeApi;

public class PokedexData {
    private int number;
    private String name;
    private String url;

    public int getNumber() {
        String[]  urlSplit = url.split("/");
        return number = Integer.parseInt(urlSplit[urlSplit.length - 1]);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
