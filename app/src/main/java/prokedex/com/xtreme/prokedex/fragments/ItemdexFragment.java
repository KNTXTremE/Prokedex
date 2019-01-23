package prokedex.com.xtreme.prokedex.fragments;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.customAdapters.ItemListAdapter;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Item;

public class ItemdexFragment extends Fragment {

    private static final String TAG = "ItemdexFragment";
    RecyclerView recyclerItemView;
    ArrayList<Item> allItem;
    ItemListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemdex, null);
        recyclerItemView = view.findViewById((R.id.item_list_recycle));
        recyclerItemView.setHasFixedSize(true);
        recyclerItemView.setNestedScrollingEnabled(true);
        recyclerItemView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerItemView.setItemAnimator(new DefaultItemAnimator());
        allItem = getData();

        adapter = new ItemListAdapter(getContext(), allItem);

        recyclerItemView.setAdapter(adapter);
        setHasOptionsMenu(true);
        return view;
    }

    private ArrayList<Item> getData() {
        ArrayList<Item> allItem = new ArrayList<>();
        for(int i = 0; i < AllItems.getItems().size(); i++){
            Item item = new Item(AllItems.getItemsImage()[i], AllItems.getItems().get(AllItems.getItemsImage()[i])[0], AllItems.getItems().get(AllItems.getItemsImage()[i])[1]);
            allItem.add(item);
        }
        return allItem;
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
                Log.d(TAG, "onQueryTextChange: Searching in ItemDex");
                return false;
            }
        });
        View searchPlate = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        super.onCreateOptionsMenu(menu, inflater);
    }
}
