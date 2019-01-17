package prokedex.com.xtreme.prokedex;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.customAdapters.AbilityListAdapter;
import prokedex.com.xtreme.prokedex.resources.Ability;
import prokedex.com.xtreme.prokedex.resources.AllItems;

public class AbilityDexActivity extends AppCompatActivity {

    private static final String TAG = "AbilityDexFragment";
    RecyclerView recyclerAbilityView;
    ArrayList<Ability> allAbility;
    AbilityListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_ability_dex);

        Toolbar toolbar = findViewById(R.id.ability_dex_toolbar);
        setSupportActionBar(toolbar);
        setTitle("AbilityDex");

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.BLACK);
            }
        } else {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerAbilityView = findViewById((R.id.ability_list_recycle));
        recyclerAbilityView.setHasFixedSize(true);
        recyclerAbilityView.setNestedScrollingEnabled(true);
        recyclerAbilityView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerAbilityView.setItemAnimator(new DefaultItemAnimator());
        allAbility = getData();

        adapter = new AbilityListAdapter(this, allAbility);

        recyclerAbilityView.setAdapter(adapter);

    }

    private ArrayList<Ability> getData() {
        ArrayList<Ability> allAbility = new ArrayList<>();
        for(int i = 1; i <= AllItems.getAbilities().size(); i++){
            Ability ability = new Ability(AllItems.getAbilities().get(i)[0], AllItems.getAbilities().get(i)[1]);
            allAbility.add(ability);
        }
        return allAbility;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
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
                Log.d(TAG, "onQueryTextChange: Searching in AbilityDex");
                return false;
            }
        });
        View searchPlate = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        searchPlate.setBackgroundColor(Color.TRANSPARENT);

        return super.onCreateOptionsMenu(menu);
    }
}
