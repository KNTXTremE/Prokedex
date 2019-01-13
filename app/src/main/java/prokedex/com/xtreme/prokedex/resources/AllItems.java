package prokedex.com.xtreme.prokedex.resources;

import android.util.SparseArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import prokedex.com.xtreme.prokedex.R;

/**
 * Created by KanTLovE on 11/17/2017.
 */

public class AllItems {

    //###POKEDEX

    private static ArrayList<String> pokemonIds = new ArrayList<>();

    private static String[] pokemonNames = {"Bulbasaur", "Ivysaur",
            "Venusaur", "Charmander",
            "Charmeleon", "Charizard",
            "Squirtle", "Wartortle",
            "Blastoise", "Caterpie",
            "Metapod", "Butterfree",
            "Weedle", "Kakuna",
            "Beedrill", "Pidgey",
            "Pidgeotto", "Pidgeot",
            "Rattata", "Raticate"};

    private static String[] pokemonNameJaps = {"フシギダネ\nFushigidane", "フシギソウ\nFushigisou",
            "フシギバナ\nFushigibana", "ヒトカゲ\nHitokage",
            "リザード\nLizardo", "リザードン\nLizardon",
            "ゼニガメ\nZenigame", "カメール\nKameil",
            "カメックス\nKamex", "キャタピー \nCaterpie",
            "トランセル\nTrancell", "バタフリー\nButterfree",
            "ビードル\nBeedle", "コクーン\nCocoon",
            "スピアー\nSpear", "ポッポ\nPoppo",
            "ピジョン\nPigeon", "ピジョット\nPigeot",
            "コラッタ\nKoratta ", "ラッタ\nRatta"};

    private static int[] resIds = {R.drawable.p001, R.drawable.p002,
            R.drawable.p003, R.drawable.p004,
            R.drawable.p005, R.drawable.p006,
            R.drawable.p007, R.drawable.p008,
            R.drawable.p009, R.drawable.p010,
            R.drawable.p011, R.drawable.p012,
            R.drawable.p013, R.drawable.p014,
            R.drawable.p015, R.drawable.p016,
            R.drawable.p017, R.drawable.p018,
            R.drawable.p019, R.drawable.p020};

    private static SparseArray<String> elements = new SparseArray<>();

    private static int[] element1s = {5, 5, 5, 2, 2, 2, 3, 3, 3, 12,
            12, 12, 12, 12, 12, 1, 1, 1, 1, 1};

    private static int[] element2s = {8, 8, 8, 0, 0, 10, 0, 0, 0, 0,
            0, 10, 8, 8, 8, 10, 10, 10, 0, 0};

    public static void addPokemonIds(){
        for (int i = 0; i < pokemonNames.length; i++){
            pokemonIds.add("#"+ String.format("%03d", i+1));
        }
    }

    public static void addElements() {
        elements.append(0, "");
        elements.append(1, "Normal");
        elements.append(2, "Fire");
        elements.append(3, "Water");
        elements.append(4, "Electric");
        elements.append(5, "Grass");
        elements.append(6, "Ice");
        elements.append(7, "Fighting");
        elements.append(8, "Poison");
        elements.append(9, "Ground");
        elements.append(10, "Flying");
        elements.append(11, "Psychic");
        elements.append(12, "Bug");
        elements.append(13, "Rock");
        elements.append(14, "Ghost");
        elements.append(15, "Dragon");
        elements.append(16, "Dark");
        elements.append(17, "Steel");
        elements.append(18, "Fairy");
    }

    private static String[] elementsColor = {"#00000000", "#a6a677", "#ef7f33", "#688ff0", "#f8cf33", "#77c651",
            "#97d7d7", "#be332c", "#9f429f", "#dfbe68", "#a68ff0", "#f85987",
            "#a6b625", "#b69e3b", "#705997", "#703bf8", "#70594a", "#b6b6cf", "#ed98aa"};

    //###MOVEDEX

    private static String[] moveCategory = {"Physical", "Special", "Status"};

    private static String[] moveCategoryColor = {"#c82619", "#505970", "#8b878b"};

    private static Map<Integer, String[]> moves = new HashMap<>(); //{No.:{"Name","Type in number","category in number","Power","Accuracy"}}

    public static void addMoves() {
        moves.put(1, new String[]{"1,0000,000 Volt Thunderbolt", "4", "1", "195", "-"});
        moves.put(2, new String[]{"Absorb", "5", "1", "20", "100"});
        moves.put(3, new String[]{"Accelerock", "13", "0", "40", "100"});
        moves.put(4, new String[]{"Acid", "8", "1", "40", "100"});
        moves.put(5, new String[]{"Acid Armor", "8", "2", "-", "-"});
        moves.put(6, new String[]{"Acid Downpour", "8", "2", "-", "-"}); //Type wrong, need to change
        moves.put(7, new String[]{"Acid Spray", "8", "1", "40", "100"});
        moves.put(8, new String[]{"Acrobatics", "10", "0", "55", "100"});
        moves.put(9, new String[]{"Acupressure", "1", "2", "-", "-"});
        moves.put(10, new String[]{"Aerial Ace", "10", "0", "60", "∞"});
        moves.put(11, new String[]{"Aeroblast", "10", "1", "100", "95"});
    }

    //###NATURE

    private static String[] stat = {"-", "Attack", "Defense", "Sp.Attack", "Sp.Defense", "Speed"};

    private static String[] flavor = {"-", "Spicy", "Sour", "Dry", "Bitter", "Sweet"};

    private static String[] flavorColor = {"#00000000", "#f5ac78", "#fae078", "#9db7f5", "#a7db8d", "#fa92b2"};

    private static Map<Integer, String[]> natures = new HashMap<>(); //{No.:{"Name", "IncStat", "DecStat", "FavFlavor", "DisFlavor"}}

    public  static void addNatures(){
        natures.put(1, new String[]{"Hardy", "0", "0"});
        natures.put(2, new String[]{"Lonely", "1", "2"});
        natures.put(3, new String[]{"Brave", "1", "5"});
        natures.put(4, new String[]{"Adamant", "1", "3"});
        natures.put(5, new String[]{"Naughty", "1", "4"});
        natures.put(6, new String[]{"Bold", "2", "1"});
        natures.put(7, new String[]{"Docile", "0", "0"});
        natures.put(8, new String[]{"Relaxed", "2", "5"});
        natures.put(9, new String[]{"Impish", "2", "3"});
        natures.put(10, new String[]{"Lax", "2", "4"});
        natures.put(11, new String[]{"Timid", "5", "1"});
        natures.put(12, new String[]{"Hasty", "5", "2"});
        natures.put(13, new String[]{"Serious", "0", "0"});
        natures.put(14, new String[]{"Jolly", "5", "3"});
        natures.put(15, new String[]{"Naive", "5", "4"});
        natures.put(16, new String[]{"Modest", "3", "1"});
        natures.put(17, new String[]{"Mild", "3", "2"});
        natures.put(18, new String[]{"Quiet", "3", "5"});
        natures.put(19, new String[]{"Bashful", "0", "0"});
        natures.put(20, new String[]{"Rash", "3", "4"});
        natures.put(21, new String[]{"Calm", "4", "1"});
        natures.put(22, new String[]{"Gentle", "4", "2"});
        natures.put(23, new String[]{"Sassy", "4", "5"});
        natures.put(24, new String[]{"Careful", "4", "3"});
        natures.put(25, new String[]{"Quirky", "0", "0"});
    }

    public static ArrayList<String> getPokemonIds() {
        return pokemonIds;
    }

    public static String getPokemonName(int i) {
        return pokemonNames[i];
    }

    public static String getPokemonNameJap(int i) {
        return pokemonNameJaps[i];
    }

    public static int getResId(int i) {
        return resIds[i];
    }

    public static SparseArray<String> getElements() {
        return elements;
    }

    public static String getElementsColor(int i) {
        return elementsColor[i];
    }

    public static int getElement1(int i) {
        return element1s[i];
    }

    public static int getElement2(int i) {
        return element2s[i];
    }

    public static String getPokemonId(int i) {
        return pokemonIds.get(i);
    }

    public String[] getPokemonNames() {
        return pokemonNames;
    }

    public String[] getPokemonNameJaps() {
        return pokemonNameJaps;
    }

    public static int[] getResIds() {
        return resIds;
    }

    public int[] getElement1s() {
        return element1s;
    }

    public int[] getElement2s() {
        return element2s;
    }

    public static String[] getMoveCategory() {
        return moveCategory;
    }

    public static String getMoveCategoryColor(int i) {
        return moveCategoryColor[i];
    }

    public static Map<Integer, String[]> getMoves() {
        return moves;
    }

    public static String getStat(int i) {
        return stat[i];
    }

    public static String getFlavor(int i) {
        return flavor[i];
    }

    public static Map<Integer, String[]> getNatures() {
        return natures;
    }

    public static String getFlavorColor(int i) {
        return flavorColor[i];
    }
}
