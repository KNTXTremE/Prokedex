package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.PokeDescActivity;
import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Pokemon;

/**
 * Created by KanTLovE on 11/13/2017.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Pokemon> pokemons;
    private ArrayList<Pokemon> pokemonsTemp;
    private Context context;

    private static final String TAG = "RecyclerViewAdapter";

    public PokemonListAdapter(Context context, ArrayList<Pokemon> pokemons){
        inflater = LayoutInflater.from(context);
        this.pokemons = pokemons;
        this.context = context;
        Log.d(TAG, "PokemonListAdapter: " + this.pokemons.size());
        this.pokemonsTemp = (ArrayList<Pokemon>) pokemons.clone();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.pokemon_lists, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder viewHolder, final int position) {
        viewHolder.pokemonId.setText(pokemons.get(position).getId());
        viewHolder.name.setText(pokemons.get(position).getName());
        viewHolder.nameJap.setText(pokemons.get(position).getNameJap());
        viewHolder.element1.setText(AllItems.getElements().get(pokemons.get(position).getElement1()));
        viewHolder.element1.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(pokemons.get(position).getElement1())));
        viewHolder.element2.setText(AllItems.getElements().get(pokemons.get(position).getElement2()));
        viewHolder.element2.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(pokemons.get(position).getElement2())));
        viewHolder.sprite.setBackgroundResource(pokemons.get(position).getResId());
        viewHolder.isCaught.setChecked(pokemons.get(position).isCaught());

        viewHolder.isCaught.setTag(position);
        viewHolder.isCaught.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pos = (Integer) viewHolder.isCaught.getTag();

                if (pokemons.get(pos).isCaught()) {
                    pokemons.get(pos).setCaught(false);
                    Snackbar.make(v, "\"" + pokemons.get(pos).getName() + "\" mark as uncaught!", Snackbar.LENGTH_LONG).show();
                } else {
                    pokemons.get(pos).setCaught(true);
                    Snackbar.make(v, "\"" + pokemons.get(pos).getName() + "\" mark as caught!", Snackbar.LENGTH_LONG).show();
                }
            }
        });

        Bitmap bitmap = ((BitmapDrawable) viewHolder.sprite.getBackground()).getBitmap();
        Palette palette = Palette.from(bitmap).maximumColorCount(24).generate();
        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();

        Palette.Swatch currentSwatch = lightMutedSwatch;
        if(currentSwatch != null)
            viewHolder.cardView.setCardBackgroundColor(currentSwatch.getRgb());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + pokemons.get(position));

                Intent intent = new Intent(context, PokeDescActivity.class);
                intent.putExtra("pokemon_id", pokemons.get(position).getId().subSequence(1,4));
                Log.d(TAG, "onClick: " + pokemons.get(position).getId().subSequence(1,4));
                intent.putExtra("pokemon_isCaught", pokemons.get(position).isCaught());
                context.startActivity(intent);
            }
        });
    }

    public int getItemCount(){
        return pokemons.size();
    }

    public void filter(String text) {

        if(!text.isEmpty()) {
            pokemons.clear();
//            Log.d(TAG, "filter: " + pokemonsTemp.size());
            for (Pokemon p : pokemonsTemp) {
                if (p.getName().toLowerCase().contains(text.toLowerCase())
                        || p.getNameJap().toLowerCase().contains((text.toLowerCase()))
                        || AllItems.getElements().get(p.getElement1()).toLowerCase().contains(text.toLowerCase())
                        || AllItems.getElements().get(p.getElement2()).toLowerCase().contains(text.toLowerCase())) {
                    pokemons.add(p);
                }
            }
        } else{
            pokemons = (ArrayList<Pokemon>) pokemonsTemp.clone();
        }
        notifyDataSetChanged();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView pokemonId;
        private TextView name;
        private TextView nameJap;
        private TextView element1;
        private TextView element2;
        private ImageView sprite;
        private CheckBox isCaught;

        private MyViewHolder(View v) {
            super(v);
            this.cardView = v.findViewById(R.id.cardview_pokedex);
            this.pokemonId = v.findViewById(R.id.pokedex_id);
            this.name = v.findViewById(R.id.pokedex_name);
            this.nameJap = v.findViewById(R.id.pokedex_name_jap);
            this.element1 = v.findViewById(R.id.pokedex_element1);
            this.element2 = v.findViewById(R.id.pokedex_element2);
            this.sprite = v.findViewById(R.id.pokedex_sprite);
            this.isCaught = v.findViewById(R.id.pokedex_is_caught);
        }


    }
}
