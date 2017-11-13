package prokedex.com.xtreme.prokedex;

import android.content.Context;
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

public class PokemonListAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Pokemon> pokemon;

    public PokemonListAdapter(Context context, ArrayList<Pokemon> pokemon) {
        this.mContext= context;
        this.pokemon = pokemon;
    }

    public int getCount() {
        return pokemon.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(view == null)
            view = mInflater.inflate(R.layout.pokemon_lists, parent, false);

        TextView textView1 = (TextView)view.findViewById(R.id.textView1);
        textView1.setText(pokemon.get(position).getName());

        TextView textView2 = (TextView)view.findViewById(R.id.textView2);
        textView2.setText(pokemon.get(position).getNameJap());

        TextView textView3 = (TextView)view.findViewById(R.id.textView3);
        textView3.setText(pokemon.get(position).getElement1());

        TextView textView4 = (TextView)view.findViewById(R.id.textView4);
        textView4.setText(pokemon.get(position).getElement2());

        ImageView imageView = (ImageView)view.findViewById(R.id.imageView1);
        imageView.setBackgroundResource(pokemon.get(position).getResId());

        return view;
    }
}
