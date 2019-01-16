package prokedex.com.xtreme.prokedex;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import prokedex.com.xtreme.prokedex.fragments.ItemdexFragment;
import prokedex.com.xtreme.prokedex.fragments.MoreFragment;
import prokedex.com.xtreme.prokedex.fragments.MovedexFragment;
import prokedex.com.xtreme.prokedex.fragments.NaturesFragment;
import prokedex.com.xtreme.prokedex.fragments.PokedexFragment;
import prokedex.com.xtreme.prokedex.resources.AllItems;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    public static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int darkModeEnabled = prefs.getInt("dark_mode_enabled", AppCompatDelegate.MODE_NIGHT_NO);
        Log.d(TAG, "onCreate: " + darkModeEnabled);
        AppCompatDelegate.setDefaultNightMode(darkModeEnabled);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);

        BottomNavigationView bottomNavView = findViewById(R.id.bottom_navigation);
        bottomNavView.setSelectedItemId(R.id.bottom_nav_pokedex);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item);
                return true;
            }
        });

        setDarkMode(bottomNavView);

        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        Fragment prevFragment = manager.findFragmentById(R.id.pokedex_fragment_init);
        if(prevFragment == null){
            Fragment fragment = new PokedexFragment();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.pokedex_fragment_init, fragment);
            transaction.commit();
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Coming Soon", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        AllItems.addElements();
        AllItems.addMoves();
        AllItems.addPokemonIds();
        AllItems.addNatures();
        AllItems.addNaturesCal();
        AllItems.addItems();
    }

    private void setDarkMode(BottomNavigationView bottomNavView) {
        int [][] states = new int [][]{
                new int[] { android.R.attr.state_enabled, -android.R.attr.state_checked},
                new int[] {android.R.attr.state_enabled, android.R.attr.state_checked}
        };
        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            int[] colors = new int[] {
                    Color.parseColor("#909090"),
                    Color.parseColor("#ffffff"),
            };
            bottomNavView.setBackgroundColor(getResources().getColor(R.color.colorDarkPrimary));
            bottomNavView.setItemTextColor(new ColorStateList(states, colors));
            bottomNavView.setItemIconTintList(new ColorStateList(states, colors));
            Window window = this.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.BLACK);
            }
        } else {
            int[] colors = new int[] {
                    Color.parseColor("#4d4d4d"),
                    Color.parseColor("#a40000"),
            };
            bottomNavView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            bottomNavView.setItemTextColor(new ColorStateList(states, colors));
            bottomNavView.setItemIconTintList(new ColorStateList(states, colors));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.



        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    public void selectFragment(MenuItem item) {
        int id = item.getItemId();
        Fragment fragment = null;

        if(!item.isChecked()){
            if (id == R.id.bottom_nav_pokedex ) {
                Log.d(TAG, "selectFragment: Initialize Pokedex fragment");
                fragment = new PokedexFragment();
            } else if (id == R.id.bottom_nav_movedex) {
                Log.d(TAG, "selectFragment: Initialize Movedex fragment");
                fragment = new MovedexFragment();
            } else if (id == R.id.bottom_nav_itemdex) {
                Log.d(TAG, "selectFragment: Initialize Itemdex fragment");
                fragment = new ItemdexFragment();
            } else if (id == R.id.bottom_nav_natures) {
                Log.d(TAG, "selectFragment: Initialize Natures fragment");
                fragment = new NaturesFragment();
            } else if (id == R.id.bottom_nav_more) {
                Log.d(TAG, "selectFragment: Initialize More fragment");
                fragment = new MoreFragment();
            }
        }

        if(fragment != null){
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.pokedex_fragment_init, fragment);
            transaction.commit();
            item.setChecked(true);
            setTitle(item.getTitle());
        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
    }
}
