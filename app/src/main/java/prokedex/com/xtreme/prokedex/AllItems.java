package prokedex.com.xtreme.prokedex;

import android.util.SparseArray;

import java.util.HashMap;

/**
 * Created by KanTLovE on 11/17/2017.
 */

public class AllItems {
    private static String[] pokemonNames = {"Bulbasaur","Ivysaur",
            "Venusaur","Charmander",
            "Charmeleon", "Charizard",
            "Squirtle","Wartortle",
            "Blastoise", "Caterpie",
            "Metapod", "Butterfree",
            "Weedle", "Kakuna",
            "Beedrill", "Pidgey",
            "Pidgeotto", "Pidgeot",
            "Rattata", "Raticate"};

    private static String[] pokemonNameJaps = {"フシギダネ\nFushigidane","フシギソウ\nFushigisou",
            "フシギバナ\nFushigibana","ヒトカゲ\nHitokage",
            "リザード\nLizardo", "リザードン\nLizardon",
            "ゼニガメ\nZenigame","カメール\nKameil",
            "カメックス\nKamex", "キャタピー \nCaterpie",
            "トランセル\nTrancell", "バタフリー\nButterfree",
            "ビードル\nBeedle", "コクーン\nCocoon",
            "スピアー\nSpear", "ポッポ\nPoppo",
            "ピジョン\nPigeon", "ピジョット\nPigeot",
            "コラッタ\nKoratta ", "ラッタ\nRatta"};

    private static int[] resIds = { R.drawable.p001, R.drawable.p002,
            R.drawable.p003, R.drawable.p004,
            R.drawable.p005, R.drawable.p006,
            R.drawable.p007, R.drawable.p008,
            R.drawable.p009, R.drawable.p010,
            R.drawable.p011, R.drawable.p012,
            R.drawable.p013, R.drawable.p014,
            R.drawable.p015, R.drawable.p016,
            R.drawable.p017, R.drawable.p018,
            R.drawable.p019, R.drawable.p020 };

    protected static SparseArray<Integer> elements = new SparseArray<>();

    private static int[] element1s = {5, 5, 5, 2, 2, 2, 3, 3, 3, 12,
            12, 12, 12, 12, 12, 1, 1, 1, 1, 1};

    private static int[] element2s = {8, 8, 8, 0, 0, 10, 0, 0, 0, 0,
            0, 10, 8, 8, 8, 10, 10, 10, 0, 0};

    public static SparseArray<Integer> addElements() {
        elements.append(0, R.drawable.type_none);
        elements.append(1, R.drawable.type_normal);
        elements.append(2, R.drawable.type_fire);
        elements.append(3, R.drawable.type_water);
        elements.append(4, R.drawable.type_electric);
        elements.append(5, R.drawable.type_grass);
        elements.append(6, R.drawable.type_ice);
        elements.append(7, R.drawable.type_fighting);
        elements.append(8, R.drawable.type_poison);
        elements.append(9, R.drawable.type_ground);
        elements.append(10, R.drawable.type_flying);
        elements.append(11, R.drawable.type_psychic);
        elements.append(12, R.drawable.type_bug);
        elements.append(13, R.drawable.type_rock);
        elements.append(14, R.drawable.type_ghost);
        elements.append(15, R.drawable.type_dragon);
        elements.append(16, R.drawable.type_dark);
        elements.append(17, R.drawable.type_steel);
        elements.append(18, R.drawable.type_fairy);
        return elements;
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
}
