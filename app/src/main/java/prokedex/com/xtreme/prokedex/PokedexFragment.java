package prokedex.com.xtreme.prokedex;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PokedexFragment extends Fragment {

    RecyclerView recyclerPokemonView;
    ArrayList<Pokemon> allPokemon;
    PokemonListAdapter adapter;
    private final String TAG = "PokedexFragment";

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
        setHasOptionsMenu(true);
        return view;
    }

    private ArrayList<Pokemon> getData(){
        ArrayList<Pokemon> allPokemon = new ArrayList<>();
        for(int i = 0; i < AllItems.getResIds().length; i++){
            Pokemon pkm = new Pokemon(AllItems.getPokemonId(i), AllItems.getPokemonName(i), AllItems.getPokemonNameJap(i), AllItems.getResId(i),AllItems.getElement1(i), AllItems.getElement2(i));
            allPokemon.add(pkm);
        }
        return allPokemon;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.filter(newText);
                Log.d(TAG, "onQueryTextChange: Searching in Pokedex");
                return false;
            }
        });

        View searchPlate = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        super.onCreateOptionsMenu(menu, inflater);
    }
}