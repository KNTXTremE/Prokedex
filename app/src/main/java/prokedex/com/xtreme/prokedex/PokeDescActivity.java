package prokedex.com.xtreme.prokedex;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import prokedex.com.xtreme.prokedex.resources.AllItems;

public class PokeDescActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = "PokeDescActivity";

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView smallTitle;
    private AppBarLayout pokedescAppBar;
    private Toolbar pokedescToolBar;
    private TextView bigTitle;
    private ImageView imgPlaceHolder;
    private FrameLayout frameLayoutTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedesc);
        Log.d(TAG, "onCreate: started.");

        pokedescToolBar        = findViewById(R.id.pokedesc_toolbar);
        bigTitle = findViewById(R.id.pokedesc_name_big);
        smallTitle          = findViewById(R.id.pokedesc_name_small);
        mTitleContainer = findViewById(R.id.main_linearlayout_title);
        pokedescAppBar   = findViewById(R.id.pokedesc_app_bar);
        imgPlaceHolder = findViewById(R.id.pokedesc_imageview_placeholder);
        frameLayoutTitle = findViewById(R.id.pokedesc_framelayout_title);

        pokedescAppBar.addOnOffsetChangedListener(this);

        startAlphaAnimation(smallTitle, 0, View.INVISIBLE);

        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: check for incoming intents");
        if(getIntent().hasExtra("pokemon_id") && getIntent().hasExtra("pokemon_isCaught")){
            Log.d(TAG, "getIncomingIntent: found intents!");
            Integer pokemon_id = Integer.parseInt(getIntent().getStringExtra("pokemon_id"))-1;
            Log.d(TAG, "getIncomingIntent: " + getIntent().getStringExtra("pokemon_id"));
            boolean pokemon_isCaught = getIntent().getBooleanExtra("pokemon_isCaught", false);
            setItems(pokemon_id, pokemon_isCaught);

        }
    }

    private void setItems(Integer pokemonId, boolean pokemon_isCaught){
        Log.d(TAG, "setItems: settings items to the widgets");
        String pokemonIdReal = "#" + String.format("%03d", pokemonId+1);
        TextView pokedescId = findViewById(R.id.pokedesc_id);
        pokedescId.setText(pokemonIdReal);

        TextView pokedescName = findViewById(R.id.pokedesc_name);
        pokedescName.setText(AllItems.getPokemonName(pokemonId));

        TextView pokedescNameJap = findViewById(R.id.pokedesc_name_jap);
        pokedescNameJap.setText(AllItems.getPokemonNameJap(pokemonId));
//
        ImageView pokedescImage = findViewById(R.id.pokedesc_sprite);
        Glide.with(this).load(AllItems.getResId(pokemonId)).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                Palette palette = Palette.from(bitmap).maximumColorCount(24).generate();
                Palette.Swatch mutedSwatch = palette.getMutedSwatch();
                int color = mutedSwatch.getRgb();

                pokedescAppBar.setBackgroundColor(color);
                pokedescToolBar.setBackgroundColor(color);
                Window window = PokeDescActivity.this.getWindow();
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(color);
                }
                frameLayoutTitle.setBackgroundColor(color);
                imgPlaceHolder.setBackgroundColor(color);
                return false;
            }
        }).into(pokedescImage);
//        pokedescImage.setBackgroundResource(AllItems.getResId(pokemonId));
//
        TextView pokedescElement1 = findViewById(R.id.pokedesc_element1);
        pokedescElement1.setText(AllItems.getElements().get(AllItems.getElement1(pokemonId)));
        pokedescElement1.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(AllItems.getElement1(pokemonId))));

        TextView pokedescElement2 = findViewById(R.id.pokedesc_element2);
        pokedescElement2.setText(AllItems.getElements().get(AllItems.getElement2(pokemonId)));
        pokedescElement2.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(AllItems.getElement2(pokemonId))));

        TextView pokedescIsCaught = findViewById(R.id.pokedesc_is_caught);
        if(pokemon_isCaught){
            pokedescIsCaught.setText("Caught");
        }
        else{
            pokedescIsCaught.setText("Not Caught");
        }
//
//        AppBarLayout pokedescAppBar = findViewById(R.id.pokedesc_app_bar);
//        Toolbar pokedescToolBar = findViewById(R.id.pokedesc_toolbar);
        setSupportActionBar(pokedescToolBar);
        pokedescToolBar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        bigTitle.setText(AllItems.getPokemonName(pokemonId));
        smallTitle.setText(AllItems.getPokemonName(pokemonId));
        setTitle("");
//        pokedescToolBar.setTitleTextColor(Color.WHITE);
//
//        CardView pokedescCard = findViewById(R.id.cardview_pokedesc);
//        Bitmap bitmap = ((BitmapDrawable) pokedescImage.getBackground()).getBitmap();
//        Palette palette = Palette.from(bitmap).maximumColorCount(24).generate();
//        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
//        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
//        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
//        Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
//        Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
//        Palette.Swatch currentSwatch;
//        Palette.Swatch currentDarkenSwatch;
//
//        currentSwatch = lightMutedSwatch; //Change Swatch here!
//        currentDarkenSwatch = mutedSwatch;
//        if(currentSwatch != null)
////            pokedescCard.setCardBackgroundColor(currentSwatch.getRgb());
//        if(currentDarkenSwatch != null) {
//            Log.d(TAG, "setItems: " + currentDarkenSwatch.getRgb());
//            pokedescAppBar.setBackgroundColor(currentDarkenSwatch.getRgb());
//            pokedescToolBar.setBackgroundColor(currentDarkenSwatch.getRgb());
//        }

        pokedescToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(smallTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(smallTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}
