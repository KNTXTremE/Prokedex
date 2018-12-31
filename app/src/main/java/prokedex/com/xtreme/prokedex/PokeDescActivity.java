package prokedex.com.xtreme.prokedex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class PokeDescActivity extends AppCompatActivity {

    private static final String TAG = "PokeDescActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedesc);
        Log.d(TAG, "onCreate: started.");
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: check for incoming intents");
        if(getIntent().hasExtra("pokemon_name") && getIntent().hasExtra("imageId")){
            Log.d(TAG, "getIncomingIntent: found intents!");
            String pokemon_name = getIntent().getStringExtra("pokemon_name");
            int imageId = getIntent().getIntExtra("imageId", 0);
            setItems(pokemon_name, imageId);

        }
    }

    private void setItems(String pokemonName, int imageId){
        Log.d(TAG, "setItems: settings items to the widgets");
        TextView name = findViewById(R.id.pokemon_name_desc);
        name.setText(pokemonName);

        ImageView image = findViewById(R.id.image_desc);
        image.setBackgroundResource(imageId);
        Toolbar pokedescToolBar = findViewById(R.id.pokedesc_toolbar);
        setSupportActionBar(pokedescToolBar);
        pokedescToolBar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        setTitle(AllItems.getPokemonName(pokemonId));
        pokedescToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
