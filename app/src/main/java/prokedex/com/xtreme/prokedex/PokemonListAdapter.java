package prokedex.com.xtreme.prokedex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by KanTLovE on 11/13/2017.
 */

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    private ArrayList<Pokemon> pokemons;


    public PokemonListAdapter(Context context, ArrayList<Pokemon> pokemons){
        inflater = LayoutInflater.from(context);
        this.pokemons = pokemons;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.pokemon_lists, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.name.setText(pokemons.get(position).getName());
        viewHolder.nameJap.setText(pokemons.get(position).getNameJap());
        viewHolder.element1.setText(pokemons.get(position).getElement1());
        viewHolder.element2.setText(pokemons.get(position).getElement2());
        viewHolder.sprite.setBackgroundResource(pokemons.get(position).getResId());

    }

    public int getItemCount(){
        return 9;
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        protected TextView name;
        protected TextView nameJap;
        protected TextView element1;
        protected TextView element2;
        protected ImageView sprite;

        private MyViewHolder(View v) {
            super(v);
            this.name = (TextView) v.findViewById(R.id.textView1);
            this.nameJap = (TextView) v.findViewById(R.id.textView2);
            this.element1 = (TextView) v.findViewById(R.id.textView3);
            this.element2 = (TextView) v.findViewById(R.id.textView4);
            this.sprite = (ImageView) v.findViewById(R.id.imageView1);
        }
    }
}
