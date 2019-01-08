package prokedex.com.xtreme.prokedex;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class IVCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.DarkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        setContentView(R.layout.activity_iv_calculator);

        Toolbar toolbar = findViewById(R.id.iv_calculator_toolbar);
        setSupportActionBar(toolbar);
        setTitle("IV Calculator");

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

    }

    public int[] calculateHP(int base, int level, int hp, int iv, int ev){
        if(hp == -1 && iv != -1 && ev != -1){
            hp = ((2*base + iv + ev/4 + 100) * level) / 100 + 10;       //CASE CALCULATE FOR HP
        } else if(iv == -1 && hp != -1 && ev != -1){
            iv = ((hp - 10) * 100) / level - 2*base - ev/4 - 100;       //CASE CALCULATE FOR IV
        } else if(ev == -1 && hp != -1 && iv != -1){
            ev = (((hp - 10) * 100) / level - 2*base - iv - 100) * 4;   //CASE CALCULATE FOR EV
        }
        return new int[] {hp, iv, ev};
    }

    public int[] calculateStat(int base, int level, int nature, int stat, int iv, int ev){
        if(stat == -1 && iv != -1 && ev != -1){
            stat = (((2*base + iv + ev/4) * level) / 100 + 5) * nature; //CASE CALCULATE FOR STAT
        } else if(iv == -1 && stat != -1 && ev != -1){
            iv = ((stat/nature - 5) * 100) / level - 2*base - ev/4;     //CASE CALCULATE FOR IV
        } else if(ev == -1 && stat != -1 && iv != -1){
            ev = (((stat/nature - 5) * 100) / level - 2*base - iv) * 4; //CASE CALCULATE FOR EV
        }
        return new int[] {stat, iv, ev};
    }
}
