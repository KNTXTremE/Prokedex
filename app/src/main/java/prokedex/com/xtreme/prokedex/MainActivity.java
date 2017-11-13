package prokedex.com.xtreme.prokedex;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected String[] pokemonName = {"Bulbasaur","Ivysaur",
            "Venusaur","Charmander",
            "Charmeleon", "Charizard",
            "Squirtle","Wartortle",
            "Blastoise"};

    protected String[] pokemonNameJap = {"フシギダネ\nFushigidane","フシギソウ\nFushigisou",
            "フシギバナ\nFushigibana","ヒトカゲ\nHitokage",
            "リザード\nLizardo", "リザードン\nLizardon",
            "ゼニガメ\nZenigame","カメール\nKameil",
            "カメックス\nKamex"};

    protected int[] resId = { R.drawable.p001, R.drawable.p002,
            R.drawable.p003, R.drawable.p004,
            R.drawable.p005, R.drawable.p006,
            R.drawable.p007, R.drawable.p008,
            R.drawable.p009 };

    protected String[] element1 = {"Grass", "Grass",
            "Grass", "Fire",
            "Fire", "Fire",
            "Water", "Water",
            "Water"};

    protected String[] element2 = {"Poison", "Poison",
            "Poison", "",
            "", "Flying",
            "", "",
            ""};

    protected ArrayList<Pokemon> allPokemon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Coming Soon", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        show_list();
    }

    private void show_list() {
        for(int i = 0; i < resId.length; i++){
            Pokemon pkm = new Pokemon(pokemonName[i], pokemonNameJap[i], resId[i], element1[i], element2[i]);
            allPokemon.add(pkm);
        }
        ListAdapter adapter = new PokemonListAdapter(getApplicationContext(), allPokemon);
        ListView data_view = (ListView)this.findViewById(R.id.pokemon_list);
        data_view.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
