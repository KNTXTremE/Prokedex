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
        if(getIntent().hasExtra("pokemon_id") && getIntent().hasExtra("pokemon_isCaught")){
            Log.d(TAG, "getIncomingIntent: found intents!");
            Integer pokemon_id = Integer.parseInt(getIntent().getStringExtra("pokemon_id"));
            boolean pokemon_isCaught = getIntent().getBooleanExtra("pokemon_isCaught", false);
            setItems(pokemon_id, pokemon_isCaught);

        }
    }

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
