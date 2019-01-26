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
            "Rattata", "Raticate",
            "Spearow", "Fearow",
            "Ekans", "Arbok",
            "Pikachu", "Raichu",
            "Sandshrew", "Sandslash",
            "Nidoran♀", "Nidorina",
            "Nidoqueen", "Nidoran♂",
            "Nidorino", "Nidoking",
            "Clefairy", "Clefable",
            "Vulpix", "Ninetales",
            "Jigglypuff", "Wigglytuff",
            "Zubat", "Golbat",
            "Oddish", "Gloom",
            "Vileplume", "Paras",
            "Parasect", "Venonat",
            "Venomoth", "Diglett",
            "Dugtrio", "Meowth",
            "Persian", "Psyduck",
            "Golduck", "Mankey",
            "Primeape", "Growlithe",
            "Arcanine", "Poliwag"};

    private static String[] pokemonNameJaps = {"フシギダネ\nFushigidane", "フシギソウ\nFushigisou",
            "フシギバナ\nFushigibana", "ヒトカゲ\nHitokage",
            "リザード\nLizardo", "リザードン\nLizardon",
            "ゼニガメ\nZenigame", "カメール\nKameil",
            "カメックス\nKamex", "キャタピー \nCaterpie",
            "トランセル\nTrancell", "バタフリー\nButterfree",
            "ビードル\nBeedle", "コクーン\nCocoon",
            "スピアー\nSpear", "ポッポ\nPoppo",
            "ピジョン\nPigeon", "ピジョット\nPigeot",
            "コラッタ\nKoratta ", "ラッタ\nRatta",
            "オニスズメ\nOnisuzume", "オニドリル\nOnidrill",
            "アーボ\nArbo", "アーボック\nArbok",
            "ピカチュウ\nPikachu", "ライチュウ\nRaichu",
            "サンド\nSand", "サンドパン\nSandpan",
            "ニドラン♀\nNidoran♀", "ニドリーナ\nNidorina",
            "ニドクイン\nNidoqueen", "ニドラン♂\nNidoran♂",
            "ニドリーノ\nNidorino", "ニドキング\nNidoking",
            "ピッピ\nPippi", "ピクシー\nPixy",
            "ロコン\nRokon", "キュウコン\nKyukon",
            "プリン\nPurin", "プクリン\nPukurin",
            "ズバット\nZubat", "ゴルバット\nGolbat",
            "ナゾノクサ\nNazonokusa", "クサイハナ\nKusaihana",
            "ラフレシア\nRuffresia", "パラス\nParas",
            "パラセクト\nParasect", "コンパン\nKongpang",
            "モルフォン\nMorphon", "ディグダ\nDigda",
            "ダグトリオ\nDugtrio", "ニャース\nNyarth",
            "ペルシアン\nPersian", "コダック\nKoduck",
            "ゴルダック\nGolduck", "マンキー\nMankey",
            "オコリザル\nOkorizaru", "ガーディ\nGardie",
            "ウインディ\nWindie", "ニョロモ\nNyoromo"};

    private static int[] resIds = {R.drawable.p001, R.drawable.p002,
            R.drawable.p003, R.drawable.p004,
            R.drawable.p005, R.drawable.p006,
            R.drawable.p007, R.drawable.p008,
            R.drawable.p009, R.drawable.p010,
            R.drawable.p011, R.drawable.p012,
            R.drawable.p013, R.drawable.p014,
            R.drawable.p015, R.drawable.p016,
            R.drawable.p017, R.drawable.p018,
            R.drawable.p019, R.drawable.p020,
            R.drawable.p021, R.drawable.p022,
            R.drawable.p023, R.drawable.p024,
            R.drawable.p025, R.drawable.p026,
            R.drawable.p027, R.drawable.p028,
            R.drawable.p029, R.drawable.p030,
            R.drawable.p031, R.drawable.p032,
            R.drawable.p033, R.drawable.p034,
            R.drawable.p035, R.drawable.p036,
            R.drawable.p037, R.drawable.p038,
            R.drawable.p039, R.drawable.p040,
            R.drawable.p041, R.drawable.p042,
            R.drawable.p043, R.drawable.p044,
            R.drawable.p045, R.drawable.p046,
            R.drawable.p047, R.drawable.p048,
            R.drawable.p049, R.drawable.p050,
            R.drawable.p051, R.drawable.p052,
            R.drawable.p053, R.drawable.p054,
            R.drawable.p055, R.drawable.p056,
            R.drawable.p057, R.drawable.p058,
            R.drawable.p059, R.drawable.p060};

    private static SparseArray<String> elements = new SparseArray<>();

    private static int[] element1s = {5, 5, 5, 2, 2, 2, 3, 3, 3, 12,
            12, 12, 12, 12, 12, 1, 1, 1, 1, 1,
            1, 1, 8, 8, 4, 4, 9, 9, 8, 8,
            8, 8, 8, 8, 18, 18, 2, 2, 1, 1,
            8, 8, 5, 5, 5, 12, 12, 12, 12, 9,
            9, 1, 1, 3, 3, 7, 7, 2, 2, 3};

    private static int[] element2s = {8, 8, 8, 0, 0, 10, 0, 0, 0, 0,
            0, 10, 8, 8, 8, 10, 10, 10, 0, 0,
            10, 10, 0, 0, 0, 0, 0, 0, 0, 0,
            9, 0, 0, 9, 0, 0, 0, 0, 18, 18,
            10, 10, 8, 8, 8, 5, 5, 8, 8, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

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
        elements.append(14, "Ghost");elements.append(15, "Dragon");
        elements.append(16, "Dark");elements.append(17, "Steel");
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

    public static void addNatures(){
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

    private static Map<String, String[]> naturesCal = new HashMap<>(); //{No.:{"Name", "IncStat", "DecStat", "FavFlavor", "DisFlavor"}}

    public static void addNaturesCal(){
        naturesCal.put("Hardy", new String[]{"0", "0"});
        naturesCal.put("Lonely", new String[]{"1", "2"});
        naturesCal.put("Brave", new String[]{"1", "5"});
        naturesCal.put("Adamant", new String[]{"1", "3"});
        naturesCal.put("Naughty", new String[]{"1", "4"});
        naturesCal.put("Bold", new String[]{"2", "1"});
        naturesCal.put("Docile", new String[]{"0", "0"});
        naturesCal.put("Relaxed", new String[]{"2", "5"});
        naturesCal.put("Impish", new String[]{"2", "3"});
        naturesCal.put("Lax", new String[]{"2", "4"});
        naturesCal.put("Timid", new String[]{"5", "1"});
        naturesCal.put("Hasty", new String[]{"5", "2"});
        naturesCal.put("Serious", new String[]{"0", "0"});
        naturesCal.put("Jolly", new String[]{"5", "3"});
        naturesCal.put("Naive", new String[]{"5", "4"});
        naturesCal.put("Modest", new String[]{"3", "1"});
        naturesCal.put("Mild", new String[]{"3", "2"});
        naturesCal.put("Quiet", new String[]{"3", "5"});
        naturesCal.put("Bashful", new String[]{"0", "0"});
        naturesCal.put("Rash", new String[]{"3", "4"});
        naturesCal.put("Calm", new String[]{"4", "1"});
        naturesCal.put("Gentle", new String[]{"4", "2"});
        naturesCal.put("Sassy", new String[]{"4", "5"});
        naturesCal.put("Careful", new String[]{"4", "3"});
        naturesCal.put("Quirky", new String[]{"0", "0"});
    }

    //###ITEM

    private static Map<Integer, String[]> items = new HashMap<>();

    private static int[] itemsImage = {R.drawable.i001_dream_ability_capsule_sprite,
            R.drawable.i002_bag_ability_urge_sprite,
            R.drawable.i003_dream_abomasite_sprite,
            R.drawable.i004_dream_absolite_sprite,
            R.drawable.i005_dream_absorb_bulb_sprite,
            R.drawable.i006_bag_acro_bike_sprite,
            R.drawable.i007_dream_adamant_orb_sprite,
            R.drawable.i008_dream_adrenaline_orb_sprite,
            R.drawable.i009_bag_adventure_rules_sprite,
            R.drawable.i010_dream_aerodactylite_sprite,
            R.drawable.i011_dream_aggronite_sprite,
            R.drawable.i012_dream_aguav_berry_sprite,
            R.drawable.i013_dream_air_balloon_sprite,
            R.drawable.i014_bag_air_mail_sprite,
            R.drawable.i015_dream_alakazite_sprite,
            R.drawable.i016_dream_aloraichium_z_sprite,
            R.drawable.i017_dream_altarianite_sprite,
            R.drawable.i018_dream_amaze_mulch_sprite,
            R.drawable.i019_dream_ampharosite_sprite,
            R.drawable.i020_dream_amulet_coin_sprite,};

    public static void addItems(){
        items.put(R.drawable.i001_dream_ability_capsule_sprite, new String[]{"Ability Capsule", "Allows a Pokémon with two Abilities to switch between these Abilities."});
        items.put(R.drawable.i002_bag_ability_urge_sprite, new String[]{"Ability Urge", "Activates the Ability of an ally Pokémon. Wonder Launcher only."});
        items.put(R.drawable.i003_dream_abomasite_sprite, new String[]{"Abomasite", "Allows Abomasnow to Mega Evolve into Mega Abomasnow."});
        items.put(R.drawable.i004_dream_absolite_sprite, new String[]{"Absolite", "Allows Absol to Mega Evolve into Mega Absol."});
        items.put(R.drawable.i005_dream_absorb_bulb_sprite, new String[]{"Absorb Bulb", "Raises the holder's Special Attack after being hit by a Water-type attack. Consumed after use."});
        items.put(R.drawable.i006_bag_acro_bike_sprite, new String[]{"Acro Bike", "A bicycle with which the player can do tricks such as jump sideways."});
        items.put(R.drawable.i007_dream_adamant_orb_sprite, new String[]{"Adamant Orb", "Boosts Dialga's Steel- and Dragon-type attacks."});
        items.put(R.drawable.i008_dream_adrenaline_orb_sprite, new String[]{"Adrenaline Orb", "When used, makes wild Pokémon more likely to call allies for help in an SOS Battle. Is not consumed if it fails. If the holder is affected by Intimidate, its Speed is increased by one stage."});
        items.put(R.drawable.i009_bag_adventure_rules_sprite, new String[]{"Adventure Rules", "Contains information new Trainers should know."});
        items.put(R.drawable.i010_dream_aerodactylite_sprite, new String[]{"Aerodactylite", "Allows Aerodactyl to Mega Evolve into Mega Aerodactyl."});
        items.put(R.drawable.i011_dream_aggronite_sprite, new String[]{"Aggronite", "Allows Aggron to Mega Evolve into Mega Aggron."});
        items.put(R.drawable.i012_dream_aguav_berry_sprite, new String[]{"Aguav Berry", "When HP falls below 25%, restores 50% HP, but confuses Pokémon that dislike bitter food (12.5% when below 50% before Gen. VII)."});
        items.put(R.drawable.i013_dream_air_balloon_sprite, new String[]{"Air Balloon", "Gives the holder an immunity to Ground-type moves but is popped when the holder is hit by any non-Ground-type attacks."});
        items.put(R.drawable.i014_bag_air_mail_sprite, new String[]{"Air Mail", "A red- and blue-striped stationery. To be held by a Pokémon for delivery."});
        items.put(R.drawable.i015_dream_alakazite_sprite, new String[]{"Alakazite", "Allows Alakazam to Mega Evolve into Mega Alakazam."});
        items.put(R.drawable.i016_dream_aloraichium_z_sprite, new String[]{"Aloraichium Z", "Allows Alolan Raichu to upgrade Thunderbolt to the special Z-Move Stoked Sparksurfer."});
        items.put(R.drawable.i017_dream_altarianite_sprite, new String[]{"Altarianite", "Allows Altaria to Mega Evolve into Mega Altaria."});
        items.put(R.drawable.i018_dream_amaze_mulch_sprite, new String[]{"Amaze Mulch", "Combines the effects of Boost Mulch, Rich Mulch, and Surprise Mulch: Causes an applied Berry patch's soil to dry quicker, increases final Berry harvest by 2 Berries, and makes Berry mutations more likely."});
        items.put(R.drawable.i019_dream_ampharosite_sprite, new String[]{"Ampharosite", "Allows Ampharos to Mega Evolve into Mega Ampharos."});
        items.put(R.drawable.i020_dream_amulet_coin_sprite, new String[]{"Amulet Coin", "Doubles the earned money after a battle against a Trainer."});
    }

    //###ABILITY

    private static Map<Integer, String[]> abilities = new HashMap<>();

    public static void  addAbilities(){
        abilities.put(1, new String[]{"Adaptability", "Powers up moves of the same type as the Pokémon."});
        abilities.put(2, new String[]{"Aerilate", "Normal-type moves become Flying-type moves. The power of those moves is boosted a little."});
        abilities.put(3, new String[]{"Aftermath", "Damages the attacker if it contacts the Pokémon with a finishing hit."});
        abilities.put(4, new String[]{"Air Lock", "Eliminates the effects of weather."});
        abilities.put(5, new String[]{"Analytic", "Boosts move power when the Pokémon moves last."});
        abilities.put(6, new String[]{"Anger Point", "The Pokémon is angered when it takes a critical hit, and that maxes its Attack stat."});
        abilities.put(7, new String[]{"Anticipation", "The Pokémon can sense an opposing Pokémon's dangerous moves."});
        abilities.put(8, new String[]{"Arena Trap", "Prevents opposing Pokémon from fleeing."});
        abilities.put(9, new String[]{"Aroma Veil", "Protects itself and its allies from attacks that limit their move choices."});
        abilities.put(10, new String[]{"Aura Break", "The effects of \"Aura\" Abilities are reversed to lower the power of affected moves."});
        abilities.put(11, new String[]{"Bad Dreams", "Reduces the HP of sleeping opposing Pokémon."});
        abilities.put(12, new String[]{"Battery", "Powers up ally Pokémon's special moves."});
        abilities.put(13, new String[]{"Battle Armor", "Hard armor protects the Pokémon from critical hits."});
        abilities.put(14, new String[]{"Battle Bond", "Defeating an opposing Pokémon strengthens the Pokémon's bond with its Trainer, and it becomes Ash-Greninja. Water Shuriken gets more powerful."});
        abilities.put(15, new String[]{"Beast Boost", "The Pokémon boosts its most proficient stat each time it knocks out a Pokémon."});
        abilities.put(16, new String[]{"Berserk", "Boosts the Pokémon's Sp. Atk stat when it takes a hit that causes its HP to become half or less."});
        abilities.put(17, new String[]{"Big Pecks", "Protects the Pokémon from Defense-lowering effects."});
        abilities.put(18, new String[]{"Blaze", "Powers up Fire-type moves when the Pokémon's HP is low."});
        abilities.put(19, new String[]{"Bulletproof", "Protects the Pokémon from some ball and bomb moves."});
        abilities.put(20, new String[]{"Cacophony", "Avoids sound-based moves."});
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

    public static Map<String, String[]> getNaturesCal() {
        return naturesCal;
    }

    public static Map<Integer, String[]> getItems() {
        return items;
    }

    public static int[] getItemsImage() {
        return itemsImage;
    }

    public static Map<Integer, String[]> getAbilities() {
        return abilities;
    }
}

