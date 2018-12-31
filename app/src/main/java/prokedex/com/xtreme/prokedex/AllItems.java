package prokedex.com.xtreme.prokedex;

import android.util.SparseArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by KanTLovE on 11/17/2017.
 */

public class AllItems {

    //###POKEDEX

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

    protected static SparseArray<String> elements = new SparseArray<>();

    private static int[] element1s = {5, 5, 5, 2, 2, 2, 3, 3, 3, 12,
            12, 12, 12, 12, 12, 1, 1, 1, 1, 1};

    private static int[] element2s = {8, 8, 8, 0, 0, 10, 0, 0, 0, 0,
            0, 10, 8, 8, 8, 10, 10, 10, 0, 0};

    public static SparseArray<String> addElements() {
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
        return elements;
    }

    private static String[] elementsColor = {"#00000000", "#a6a677", "#ef7f33", "#688ff0", "#f8cf33", "#77c651",
            "#97d7d7", "#be332c", "#9f429f", "#dfbe68", "#a68ff0", "#f85987",
            "#a6b625", "#b69e3b", "#705997", "#703bf8", "#70594a", "#b6b6cf", "#ed98aa"};

    //###MOVEDEX

    private static String[] moveCategory = {"Physical", "Special", "Status"};

    private static Map<Integer, String[]> moves = new HashMap<>(); //{No.:{"Name","Type in number","category in number","Power","Accuracy"}}

    public static void addMoves() {
        moves.put(1, new String[]{"1,0000,000 Volt Thunderbolt", "4", "1", "195", "-"});
        moves.put(2, new String[]{"Absorb", "5", "1", "20", "100"});
        moves.put(3, new String[]{"Accelerock", "13", "0", "40", "100"});
        moves.put(4, new String[]{"Acid", "8", "1", "40", "100"});
        moves.put(5, new String[]{"Acid Armor", "8", "2", "-", "-"});
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

    public static Map<Integer, String[]> getMoves() {
        return moves;
    }
}
