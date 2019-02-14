package prokedex.com.xtreme.prokedex;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.PokeApi.PokeApiService;
import prokedex.com.xtreme.prokedex.PokeApi.PokedexData;
import prokedex.com.xtreme.prokedex.PokeApi.PokemonDescData;
import prokedex.com.xtreme.prokedex.PokeApi.PokemonDescRequest;
import prokedex.com.xtreme.prokedex.PokeApi.PokemonRequest;
import prokedex.com.xtreme.prokedex.fragments.ItemdexFragment;
import prokedex.com.xtreme.prokedex.fragments.MoreFragment;
import prokedex.com.xtreme.prokedex.fragments.MovedexFragment;
import prokedex.com.xtreme.prokedex.fragments.NaturesFragment;
import prokedex.com.xtreme.prokedex.fragments.PokedexFragment;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    public static SharedPreferences prefs;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private MenuItem prevMenuItem;
    private static Retrofit retrofit;
    private static ArrayList<PokedexData> pokedex;
    private static ArrayList<ArrayList<PokemonDescData>> pokemonDesc;

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
        final FloatingActionButton fab = findViewById(R.id.fab);

        mPager = findViewById(R.id.view_pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        ((ScreenSlidePagerAdapter) mPagerAdapter).addFragment(new MovedexFragment());
        ((ScreenSlidePagerAdapter) mPagerAdapter).addFragment(new ItemdexFragment());
        ((ScreenSlidePagerAdapter) mPagerAdapter).addFragment(new PokedexFragment());
        ((ScreenSlidePagerAdapter) mPagerAdapter).addFragment(new NaturesFragment());
        ((ScreenSlidePagerAdapter) mPagerAdapter).addFragment(new MoreFragment());
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(2);

        final BottomNavigationView bottomNavView = findViewById(R.id.bottom_navigation);
        bottomNavView.setSelectedItemId(R.id.bottom_nav_pokedex);
        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectFragment(item, fab);
                return true;
            }
        });

        setDarkMode(bottomNavView);
        setSupportActionBar(toolbar);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 4)
                    fab.hide();
                if (prevMenuItem != null)
                    prevMenuItem.setChecked(false);
                else {
                    bottomNavView.getMenu().getItem(0).setChecked(false);
                }

                bottomNavView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavView.getMenu().getItem(position);
                setTitle(bottomNavView.getMenu().getItem(position).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        FragmentManager manager = getSupportFragmentManager();
//        Fragment prevFragment = manager.findFragmentById(R.id.pokedex_fragment_init);
//        if(prevFragment == null){
//            Fragment fragment = new PokedexFragment();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.add(R.id.pokedex_fragment_init, fragment);
//            transaction.commit();
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        setFloatingActionButtonListener(fab);

        AllItems.addElements();
        AllItems.addMoves();
        AllItems.addPokemonIds();
        AllItems.addNatures();
        AllItems.addNaturesCal();
        AllItems.addItems();
        AllItems.addAbilities();
    }

    public static void getData() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<PokemonRequest> pokemonRequestCall = service.obtainListPokemon(807, 0);
        pokemonRequestCall.enqueue(new Callback<PokemonRequest>() {
            @Override
            public void onResponse(Call<PokemonRequest> call, Response<PokemonRequest> response) {
                if(response.isSuccessful()){
                    PokemonRequest pokemonRequest = response.body();
                    pokedex = pokemonRequest.getResults();
                } else{
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<PokemonRequest> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
        for(int i = 0; i < pokedex.size(); i++){
            Call<PokemonDescRequest> pokemonDescRequestCall = service.obtainListPokemonDesc(i);
            final int finalI = i;
            pokemonDescRequestCall.enqueue(new Callback<PokemonDescRequest>() {
                @Override
                public void onResponse(Call<PokemonDescRequest> call, Response<PokemonDescRequest> response) {
                    if(response.isSuccessful()) {
                        PokemonDescRequest pokemonDescRequest = response.body();
                        pokemonDesc.set(finalI, pokemonDescRequest.getResults());
                        Log.d(TAG, "onResponse: " + pokemonDescRequest.getResults());
                    } else {
                        Log.e(TAG, "onResponse: " + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<PokemonDescRequest> call, Throwable t) {

                }
            });
        }

    }

    public static ArrayList<PokedexData> getPokedex() {
        return pokedex;
    }

    public static ArrayList<ArrayList<PokemonDescData>> getPokemonDesc() {
        return pokemonDesc;
    }

    private void setFloatingActionButtonListener(FloatingActionButton fab) {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment page = ((ScreenSlidePagerAdapter) mPagerAdapter).getItem(mPager.getCurrentItem());
//                Fragment page = getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.view_pager + ":" + );
                RecyclerView recyclerView = null;
                Log.d(TAG, "onClick: ");
                if(page != null){
                    if (mPager.getCurrentItem() == 0) {
                        recyclerView = ((MovedexFragment)page).getRecyclerMoveView();
                    }
                    if (mPager.getCurrentItem() == 1) {
                        recyclerView = ((ItemdexFragment)page).getRecyclerItemView();
                    }
                    if (mPager.getCurrentItem() == 2) {
                        recyclerView = ((PokedexFragment)page).getRecyclerPokemonView();
                    }
                    if (mPager.getCurrentItem() == 3) {
                        recyclerView = ((NaturesFragment)page).getRecyclerNatureView();
                    }
                    if (mPager.getCurrentItem() == 4) {
                        Log.d(TAG, "setFloatingActionButton: FAB More");
                    }
                }
                if(recyclerView != null){
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    layoutManager.smoothScrollToPosition(recyclerView, null,0);
                }
            }
        });
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

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

    public void selectFragment(MenuItem item, FloatingActionButton fab) {
        int id = item.getItemId();
        Fragment fragment = null;

        if(!item.isChecked()){
            switch (id){
                case R.id.bottom_nav_pokedex:
                    mPager.setCurrentItem(2);
                    break;
                case R.id.bottom_nav_movedex:
                    mPager.setCurrentItem(0);
                    break;
                case R.id.bottom_nav_itemdex:
                    mPager.setCurrentItem(1);
                    break;
                case R.id.bottom_nav_natures:
                    mPager.setCurrentItem(3);
                    break;
                case R.id.bottom_nav_more:
                    mPager.setCurrentItem(4);
                    fab.hide();
                    break;
            }
            item.setChecked(true);
            setTitle(item.getTitle());
        }

//        if(fragment != null){
//            FragmentManager manager = getSupportFragmentManager();
//            FragmentTransaction transaction = manager.beginTransaction();
//            transaction.replace(R.id.pokedex_fragment_init, fragment);
//            transaction.commit();
//            item.setChecked(true);
//            setTitle(item.getTitle());
//        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private ArrayList<Fragment> mFragmentList = new ArrayList<>();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }
}
