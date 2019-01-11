package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

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
        final MyViewHolder viewHolder = new MyViewHolder(itemView);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Log.d(TAG, "onClick: clicked on: " + pokemons.get(position));
                Intent intent = new Intent(context, PokeDescActivity.class);
                intent.putExtra("pokemon_id", pokemons.get(position).getId().subSequence(1,4));
                intent.putExtra("pokemon_isCaught", pokemons.get(position).isCaught());
                context.startActivity(intent);
            }
        });

        viewHolder.backgroundIsCaught.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) viewHolder.backgroundIsCaught.getTag();
                if(pos != RecyclerView.NO_POSITION){
                    if (pokemons.get(pos).isCaught()) {
                        pokemons.get(pos).setCaught(false);
                        viewHolder.backgroundIsCaught.setBackgroundResource(R.drawable.ic_pokemon_bg);
                        Snackbar.make(view, "\"" + pokemons.get(pos).getName() + "\" mark as uncaught!", Snackbar.LENGTH_LONG).show();
                    } else {
                        pokemons.get(pos).setCaught(true);
                        viewHolder.backgroundIsCaught.setBackgroundResource(R.drawable.ic_pokemon_bg_caught);
                        Snackbar.make(view, "\"" + pokemons.get(pos).getName() + "\" mark as caught!", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return viewHolder;
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
        int sprite = pokemons.get(position).getResId();
        Glide.with(context).load(sprite).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                Palette palette = Palette.from(bitmap).maximumColorCount(24).generate();
                Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                if(lightMutedSwatch != null)
                    viewHolder.cardView.setCardBackgroundColor(lightMutedSwatch.getRgb());
                return false;
            }
        }).into(viewHolder.sprite);
        if(pokemons.get(position).isCaught()){
            viewHolder.backgroundIsCaught.setBackgroundResource(R.drawable.ic_pokemon_bg_caught);
        }else {
            viewHolder.backgroundIsCaught.setBackgroundResource(R.drawable.ic_pokemon_bg);
        }
        viewHolder.backgroundIsCaught.setTag(position);

        setAnimation(viewHolder.itemView, position);
    }

    public int getItemCount(){
        return pokemons.size();
    }

    public void filter(String text) {

        if(!text.isEmpty()) {
            pokemons.clear();
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

    private void setAnimation(View viewToAnimate, int position)
    {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        viewToAnimate.startAnimation(animation);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private TextView pokemonId;
        private TextView name;
        private TextView nameJap;
        private TextView element1;
        private TextView element2;
        private ImageView sprite;
        private ImageView backgroundIsCaught;

        private MyViewHolder(View v) {
            super(v);
            this.cardView = v.findViewById(R.id.cardview_pokedex);
            this.pokemonId = v.findViewById(R.id.pokedex_id);
            this.name = v.findViewById(R.id.pokedex_name);
            this.nameJap = v.findViewById(R.id.pokedex_name_jap);
            this.element1 = v.findViewById(R.id.pokedex_element1);
            this.element2 = v.findViewById(R.id.pokedex_element2);
            this.sprite = v.findViewById(R.id.pokedex_sprite);
            this.backgroundIsCaught = v.findViewById(R.id.pokedex_background_is_caught);
        }
    }
}
