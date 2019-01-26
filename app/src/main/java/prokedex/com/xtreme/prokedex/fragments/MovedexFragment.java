package prokedex.com.xtreme.prokedex.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Move;
import prokedex.com.xtreme.prokedex.customAdapters.MoveListAdapter;
import prokedex.com.xtreme.prokedex.R;


public class MovedexFragment extends Fragment {

    private static final String TAG = "MovedexFragment";
    RecyclerView recyclerMoveView;
    ArrayList<Move> allMove;
    MoveListAdapter adapter;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movedex, null);
        recyclerMoveView = view.findViewById((R.id.move_list_recycle));
        recyclerMoveView.setHasFixedSize(true);
        recyclerMoveView.setNestedScrollingEnabled(true);
        recyclerMoveView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerMoveView.setItemAnimator(new DefaultItemAnimator());
        allMove = getData();

        adapter = new MoveListAdapter(getContext(), allMove);

        recyclerMoveView.setAdapter(adapter);
        recyclerMoveView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    private ArrayList<Move> getData() {
        ArrayList<Move> allMove = new ArrayList<>();
        for(int i = 1; i <= AllItems.getMoves().size(); i++){
            Move move = new Move(AllItems.getMoves().get(i)[0], Integer.parseInt(AllItems.getMoves().get(i)[1]), Integer.parseInt(AllItems.getMoves().get(i)[2]), AllItems.getMoves().get(i)[3], AllItems.getMoves().get(i)[4]);
            allMove.add(move);
        }
        return allMove;
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
                Log.d(TAG, "onQueryTextChange: Searching in MoveDex");
                return false;
            }
        });
        View searchPlate = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        super.onCreateOptionsMenu(menu, inflater);
    }

    public RecyclerView getRecyclerMoveView() {
        return recyclerMoveView;
    }
}
