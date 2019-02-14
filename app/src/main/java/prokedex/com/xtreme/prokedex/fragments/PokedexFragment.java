package prokedex.com.xtreme.prokedex.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
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
import android.widget.LinearLayout;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.MainActivity;
import prokedex.com.xtreme.prokedex.PokeApi.PokedexData;
import prokedex.com.xtreme.prokedex.PokeApi.PokemonDescData;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Pokemon;
import prokedex.com.xtreme.prokedex.customAdapters.PokemonListAdapter;
import prokedex.com.xtreme.prokedex.R;

public class PokedexFragment extends Fragment {

    RecyclerView recyclerPokemonView;
    ArrayList<Pokemon> allPokemon = new ArrayList<>();
    ArrayList<PokedexData> pokedex;
    PokemonListAdapter adapter;
    private final String TAG = "PokedexFragment";
    private ArrayList<ArrayList<PokemonDescData>> pokemonDesc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokedex, null);
//        pokedex = MainActivity.getPokedex();
//        pokemonDesc = MainActivity.getPokemonDesc();
//        getData();
        recyclerPokemonView = view.findViewById((R.id.pokemon_list_recycle));
        recyclerPokemonView.setHasFixedSize(true);
        recyclerPokemonView.setNestedScrollingEnabled(true);
        recyclerPokemonView.setItemViewCacheSize(5);
        recyclerPokemonView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerPokemonView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PokemonListAdapter(getContext(), allPokemon);
        recyclerPokemonView.setAdapter(adapter);
        recyclerPokemonView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                FloatingActionButton fab = getActivity().findViewById(R.id.fab);
                if(dy > 0){
                    fab.show();
                } else{
                    fab.hide();
                }
            }
        });
        setHasOptionsMenu(true);

        return view;
    }

    private void getData(){
        for(int i = 0; i < pokedex.size(); i++){
            PokedexData p = pokedex.get(i);
            ArrayList<PokemonDescData> arrPd = pokemonDesc.get(i);
            PokemonDescData pd = arrPd.get(i);
            Log.d(TAG, "getData: " + pd.toString());
            Log.d(TAG, "onResponse: " + p.getNumber() + " " + p.getName());
            Pokemon pkm = new Pokemon(AllItems.getPokemonId(i), AllItems.getPokemonName(i), AllItems.getPokemonNameJap(i), AllItems.getResId(i),AllItems.getElement1(i), AllItems.getElement2(i));
//            Pokemon pkm = new Pokemon(p.getNumber(), p.getName(), "", "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + p.getNumber() + ".png",AllItems.getElement1(i), AllItems.getElement2(i));
            allPokemon.add(pkm);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            mSearch.setIcon(R.drawable.ic_search_white_24dp);
        } else {
            mSearch.setIcon(R.drawable.ic_search_black_24dp);
        }

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

    public RecyclerView getRecyclerPokemonView() {
        return recyclerPokemonView;
    }
}