package prokedex.com.xtreme.prokedex;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class PokeDescActivity extends AppCompatActivity {

    private static final String TAG = "PokeDescActivity";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedesc);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: check for incoming intents");
        if(getIntent().hasExtra("pokemon_id") && getIntent().hasExtra("pokemon_isCaught")){
            Log.d(TAG, "getIncomingIntent: found intents!");
            Integer pokemon_id = Integer.parseInt(getIntent().getStringExtra("pokemon_id"))-1;
            Log.d(TAG, "getIncomingIntent: " + getIntent().getStringExtra("pokemon_id"));
            boolean pokemon_isCaught = getIntent().getBooleanExtra("pokemon_isCaught", false);
            setItems(pokemon_id, pokemon_isCaught);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setItems(Integer pokemonId, boolean pokemon_isCaught){
        Log.d(TAG, "setItems: settings items to the widgets");
        String pokemonIdReal = "#" + String.format("%03d", pokemonId+1);
        TextView pokedescId = findViewById(R.id.pokedesc_id);
        pokedescId.setText(pokemonIdReal);

        TextView pokedescName = findViewById(R.id.pokedesc_name);
        pokedescName.setText(AllItems.getPokemonName(pokemonId));

        TextView pokedescNameJap = findViewById(R.id.pokedesc_name_jap);
        pokedescNameJap.setText(AllItems.getPokemonNameJap(pokemonId));

        ImageView pokedescImage = findViewById(R.id.pokedesc_sprite);
        pokedescImage.setBackgroundResource(AllItems.getResId(pokemonId));

        TextView pokedescElement1 = findViewById(R.id.pokedesc_element1);
        pokedescElement1.setText(AllItems.getElements().get(AllItems.getElement1(pokemonId)));
        pokedescElement1.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(AllItems.getElement1(pokemonId))));

        TextView pokedescElement2 = findViewById(R.id.pokedesc_element2);
        pokedescElement2.setText(AllItems.getElements().get(AllItems.getElement2(pokemonId)));
        pokedescElement2.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(AllItems.getElement2(pokemonId))));

        TextView pokedescIsCaught = findViewById(R.id.pokedesc_is_caught);
        if(pokemon_isCaught){
            pokedescIsCaught.setText("Caught");
        }
        else{
            pokedescIsCaught.setText("NCaught");
        }

        AppBarLayout pokedescAppBar = findViewById(R.id.pokedesc_app_bar);
        Toolbar pokedescToolBar = findViewById(R.id.pokedesc_toolbar);
        setSupportActionBar(pokedescToolBar);
        pokedescToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setTitle(AllItems.getPokemonName(pokemonId));
        pokedescToolBar.setTitleTextColor(Color.WHITE);

        CardView pokedescCard = findViewById(R.id.cardview_pokedesc);
        Bitmap bitmap = ((BitmapDrawable) pokedescImage.getBackground()).getBitmap();
        Palette palette = Palette.from(bitmap).maximumColorCount(24).generate();
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
        Palette.Swatch currentSwatch = null;
        Palette.Swatch currentDarkenSwatch = null;

        currentSwatch = lightMutedSwatch; //Change Swatch here!
        currentDarkenSwatch = mutedSwatch;
        if(currentSwatch != null)
            pokedescCard.setCardBackgroundColor(currentSwatch.getRgb());
        if(currentDarkenSwatch != null) {
            Log.d(TAG, "setItems: " + currentDarkenSwatch.getRgb());
            pokedescAppBar.setBackgroundColor(currentDarkenSwatch.getRgb());
            pokedescToolBar.setBackgroundColor(currentDarkenSwatch.getRgb());
        }

        pokedescToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(currentDarkenSwatch.getRgb());
    }
}
