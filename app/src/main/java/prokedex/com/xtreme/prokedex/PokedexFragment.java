package prokedex.com.xtreme.prokedex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PokedexFragment extends Fragment {

    RecyclerView recyclerPokemonView;
    ArrayList<Pokemon> allPokemon;
    PokemonListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedex, null);
        recyclerPokemonView = view.findViewById((R.id.pokemon_list_recycle));
        recyclerPokemonView.setHasFixedSize(true);
        recyclerPokemonView.setNestedScrollingEnabled(true);
        recyclerPokemonView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerPokemonView.setItemAnimator(new DefaultItemAnimator());
        allPokemon = getData();
        adapter = new PokemonListAdapter(getContext(), allPokemon);

        recyclerPokemonView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Pokemon> getData(){
        ArrayList<Pokemon> allPokemon = new ArrayList<>();
        for(int i = 0; i < AllItems.getResIds().length; i++){
            Pokemon pkm = new Pokemon(AllItems.getPokemonName(i), AllItems.getPokemonNameJap(i), AllItems.getResId(i),AllItems.getElement1(i), AllItems.getElement2(i));
            allPokemon.add(pkm);
        }
        return allPokemon;
    }
}