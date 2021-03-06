package prokedex.com.xtreme.prokedex;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.NumberFormat;

import prokedex.com.xtreme.prokedex.resources.AllItems;

public class IVCalculatorActivity extends AppCompatActivity {

    private static final String TAG = "IVCalculatorActivity";

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

        showDialog("Please fill in only 2 values on each row and leave value that you want to know blank.", true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final EditText levelEt = findViewById(R.id.iv_calculator_level);
        final Spinner natureSpn = findViewById(R.id.iv_calculator_nature_selector);

        final EditText hpEt = findViewById(R.id.iv_calculator_hp);
        final EditText hpIvEt = findViewById(R.id.iv_calculator_hp_iv);
        final EditText hpEvEt = findViewById(R.id.iv_calculator_hp_ev);

        final EditText attackEt = findViewById(R.id.iv_calculator_attack);
        final EditText attackIvEt = findViewById(R.id.iv_calculator_attack_iv);
        final EditText attackEvEt = findViewById(R.id.iv_calculator_attack_ev);

        final EditText defenseEt = findViewById(R.id.iv_calculator_defense);
        final EditText defenseIvEt = findViewById(R.id.iv_calculator_defense_iv);
        final EditText defenseEvEt = findViewById(R.id.iv_calculator_defense_ev);

        final EditText spAtkEt = findViewById(R.id.iv_calculator_sp_attack);
        final EditText spAtkIvEt = findViewById(R.id.iv_calculator_sp_attack_iv);
        final EditText spAtkEvEt = findViewById(R.id.iv_calculator_sp_attack_ev);

        final EditText spDefEt = findViewById(R.id.iv_calculator_sp_defense);
        final EditText spDefIvEt = findViewById(R.id.iv_calculator_sp_defense_iv);
        final EditText spDefEvEt = findViewById(R.id.iv_calculator_sp_defense_ev);

        final EditText speedEt = findViewById(R.id.iv_calculator_speed);
        final EditText speedIvEt = findViewById(R.id.iv_calculator_speed_iv);
        final EditText speedEvEt = findViewById(R.id.iv_calculator_speed_ev);

        final TextView resultLevel = findViewById(R.id.iv_result_level);
        final TextView resultNature = findViewById(R.id.iv_result_nature);

        final TextView resultHp = findViewById(R.id.iv_result_hp);
        final TextView resultHpIv = findViewById(R.id.iv_result_hp_iv);
        final TextView resultHpEv = findViewById(R.id.iv_result_hp_ev);

        final TextView resultAttack = findViewById(R.id.iv_result_attack);
        final TextView resultAttackIv = findViewById(R.id.iv_result_attack_iv);
        final TextView resultAttackEv = findViewById(R.id.iv_result_attack_ev);

        final TextView resultDefense = findViewById(R.id.iv_result_defense);
        final TextView resultDefenseIv = findViewById(R.id.iv_result_defense_iv);
        final TextView resultDefenseEv = findViewById(R.id.iv_result_defense_ev);

        final TextView resultSpAtk = findViewById(R.id.iv_result_sp_atk);
        final TextView resultSpAtkIv = findViewById(R.id.iv_result_sp_atk_iv);
        final TextView resultSpAtkEv = findViewById(R.id.iv_result_sp_atk_ev);

        final TextView resultSpDef = findViewById(R.id.iv_result_sp_def);
        final TextView resultSpDefIv = findViewById(R.id.iv_result_sp_def_iv);
        final TextView resultSpDefEv = findViewById(R.id.iv_result_sp_def_ev);

        final TextView resultSpeed = findViewById(R.id.iv_result_speed);
        final TextView resultSpeedIv = findViewById(R.id.iv_result_speed_iv);
        final TextView resultSpeedEv = findViewById(R.id.iv_result_speed_ev);

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultLevel.setText("-");
                resultNature.setText("Nature");
                resultHp.setText("-");
                resultHpIv.setText("-");
                resultHpEv.setText("-");
                resultAttack.setText("-");
                resultAttackIv.setText("-");
                resultAttackEv.setText("-");
                resultDefense.setText("-");
                resultDefenseIv.setText("-");
                resultDefenseEv.setText("-");
                resultSpAtk.setText("-");
                resultSpAtkIv.setText("-");
                resultSpAtkEv.setText("-");
                resultSpDef.setText("-");
                resultSpDefIv.setText("-");
                resultSpDefEv.setText("-");
                resultSpeed.setText("-");
                resultSpeedIv.setText("-");
                resultSpeedEv.setText("-");
                natureSpn.setSelection(0);
                levelEt.getText().clear();
                hpEt.getText().clear();
                hpIvEt.getText().clear();
                hpEvEt.getText().clear();
                attackEt.getText().clear();
                attackIvEt.getText().clear();
                attackEvEt.getText().clear();
                defenseEt.getText().clear();
                defenseIvEt.getText().clear();
                defenseEvEt.getText().clear();
                spAtkEt.getText().clear();
                spAtkIvEt.getText().clear();
                spAtkEvEt.getText().clear();
                spDefEt.getText().clear();
                spDefIvEt.getText().clear();
                spDefEvEt.getText().clear();
                speedEt.getText().clear();
                speedIvEt.getText().clear();
                speedEvEt.getText().clear();
            }
        });

        Button calculateButton = findViewById(R.id.button_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String level = levelEt.getText().toString();
                String nature = natureSpn.getSelectedItem().toString();
                Log.d(TAG, "onClick: " + nature);

                String hp = hpEt.getText().toString();
                String hp_iv = hpIvEt.getText().toString();
                String hp_ev = hpEvEt.getText().toString();

                String attack = attackEt.getText().toString();
                String attack_iv = attackIvEt.getText().toString();
                String attack_ev = attackEvEt.getText().toString();

                String defense = defenseEt.getText().toString();
                String defense_iv = defenseIvEt.getText().toString();
                String defense_ev = defenseEvEt.getText().toString();

                String sp_atk = spAtkEt.getText().toString();
                String sp_atk_iv = spAtkIvEt.getText().toString();
                String sp_atk_ev = spAtkEvEt.getText().toString();

                String sp_def = spDefEt.getText().toString();
                String sp_def_iv = spDefIvEt.getText().toString();
                String sp_def_ev = spDefEvEt.getText().toString();

                String speed = speedEt.getText().toString();
                String speed_iv = speedIvEt.getText().toString();
                String speed_ev = speedEvEt.getText().toString();

                if(level.equals("") || nature.equals("Choose Nature")){
                    showDialog("Please fill in the level and nature.", false);
                } else {
                    resultLevel.setText(level);
                    resultNature.setText(nature);
                    if((hp.equals("") && !hp_iv.equals("") && !hp_ev.equals("")) ||
                            (!hp.equals("") && hp_iv.equals("") && !hp_ev.equals("")) ||
                            (!hp.equals("") && !hp_iv.equals("") && hp_ev.equals(""))){
                        if(hp.equals(""))
                            hp = "-1";
                        else if (hp_iv.equals(""))
                            hp_iv = "-1";
                        else if (hp_ev.equals(""))
                            hp_ev = "-1";
                        int[] result = calculateHP(45, Integer.parseInt(level), Integer.parseInt(hp), Integer.parseInt(hp_iv), Integer.parseInt(hp_ev));
                        resultHp.setText(result[0] + "");
                        resultHpIv.setText(result[1] + "");
                        resultHpEv.setText(result[2] + "");
                    } if((attack.equals("") && !attack_iv.equals("") && !attack_ev.equals("")) ||
                            (!attack.equals("") && attack_iv.equals("") && !attack_ev.equals("")) ||
                            (!attack.equals("") && !attack_iv.equals("") && attack_ev.equals(""))){
                        if(attack.equals(""))
                            attack = "-1";
                        else if (attack_iv.equals(""))
                            attack_iv = "-1";
                        else if (attack_ev.equals(""))
                            attack_ev = "-1";
                        double nature_multipiler = 1;
                        if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[0])).equals("Attack")){
                            nature_multipiler = 1.1;
                        } else if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[1])).equals("Attack")){
                            nature_multipiler = 0.9;
                        }
                        int[] result = calculateStat(49, Integer.parseInt(level), nature_multipiler, Integer.parseInt(attack), Integer.parseInt(attack_iv), Integer.parseInt(attack_ev));
                        resultAttack.setText(result[0] + "");
                        resultAttackIv.setText(result[1] + "");
                        resultAttackEv.setText(result[2] + "");
                    } if((defense.equals("") && !defense_iv.equals("") && !defense_ev.equals("")) ||
                            (!defense.equals("") && defense_iv.equals("") && !defense_ev.equals("")) ||
                            (!defense.equals("") && !defense_iv.equals("") && defense_ev.equals(""))){
                        if(defense.equals(""))
                            defense = "-1";
                        else if (defense_iv.equals(""))
                            defense_iv = "-1";
                        else if (defense_ev.equals(""))
                            defense_ev = "-1";
                        double nature_multipiler = 1;
                        if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[0])).equals("Defense")){
                            nature_multipiler = 1.1;
                        } else if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[1])).equals("Defense")){
                            nature_multipiler = 0.9;
                        }
                        int[] result = calculateStat(49, Integer.parseInt(level), nature_multipiler, Integer.parseInt(defense), Integer.parseInt(defense_iv), Integer.parseInt(defense_ev));
                        resultDefense.setText(result[0] + "");
                        resultDefenseIv.setText(result[1] + "");
                        resultDefenseEv.setText(result[2] + "");
                    } if((sp_atk.equals("") && !sp_atk_iv.equals("") && !sp_atk_ev.equals("")) ||
                            (!sp_atk.equals("") && sp_atk_iv.equals("") && !sp_atk_ev.equals("")) ||
                            (!sp_atk.equals("") && !sp_atk_iv.equals("") && sp_atk_ev.equals(""))){
                        if(sp_atk.equals(""))
                            sp_atk = "-1";
                        else if (sp_atk_iv.equals(""))
                            sp_atk_iv = "-1";
                        else if (sp_atk_ev.equals(""))
                            sp_atk_ev = "-1";
                        double nature_multipiler = 1;
                        if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[0])).equals("Sp.Attack")){
                            nature_multipiler = 1.1;
                        } else if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[1])).equals("Sp.Attack")){
                            nature_multipiler = 0.9;
                        }
                        int[] result = calculateStat(65, Integer.parseInt(level), nature_multipiler, Integer.parseInt(sp_atk), Integer.parseInt(sp_atk_iv), Integer.parseInt(sp_atk_ev));
                        resultSpAtk.setText(result[0] + "");
                        resultSpAtkIv.setText(result[1] + "");
                        resultSpAtkEv.setText(result[2] + "");
                    } if((sp_def.equals("") && !sp_def_iv.equals("") && !sp_def_ev.equals("")) ||
                            (!sp_def.equals("") && sp_def_iv.equals("") && !sp_def_ev.equals("")) ||
                            (!sp_def.equals("") && !sp_def_iv.equals("") && sp_def_ev.equals(""))){
                        if(sp_def.equals(""))
                            sp_def = "-1";
                        else if (sp_def_iv.equals(""))
                            sp_def_iv = "-1";
                        else if (sp_def_ev.equals(""))
                            sp_def_ev = "-1";
                        double nature_multipiler = 1;
                        if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[0])).equals("Sp.Defense")){
                            nature_multipiler = 1.1;
                        } else if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[1])).equals("Sp.Defense")){
                            nature_multipiler = 0.9;
                        }
                        int[] result = calculateStat(65, Integer.parseInt(level), nature_multipiler, Integer.parseInt(sp_def), Integer.parseInt(sp_def_iv), Integer.parseInt(sp_def_ev));
                        resultSpDef.setText(result[0] + "");
                        resultSpDefIv.setText(result[1] + "");
                        resultSpDefEv.setText(result[2] + "");
                    } if((speed.equals("") && !speed_iv.equals("") && !speed_ev.equals("")) ||
                            (!speed.equals("") && speed_iv.equals("") && !speed_ev.equals("")) ||
                            (!speed.equals("") && !speed_iv.equals("") && speed_ev.equals(""))){
                        if(speed.equals(""))
                            speed = "-1";
                        else if (speed_iv.equals(""))
                            speed_iv = "-1";
                        else if (speed_ev.equals(""))
                            speed_ev = "-1";
                        double nature_multipiler = 1;
                        if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[0])).equals("Speed")){
                            nature_multipiler = 1.1;
                        } else if(AllItems.getStat(Integer.parseInt(AllItems.getNaturesCal().get(nature)[1])).equals("Speed")){
                            nature_multipiler = 0.9;
                        }
                        int[] result = calculateStat(45, Integer.parseInt(level), nature_multipiler, Integer.parseInt(speed), Integer.parseInt(speed_iv), Integer.parseInt(speed_ev));
                        resultSpeed.setText(result[0] + "");
                        resultSpeedIv.setText(result[1] + "");
                        resultSpeedEv.setText(result[2] + "");
                    }
                }
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

    public int[] calculateStat(int base, int level, double nature, double stat, double iv, double ev){
        NumberFormat floor = NumberFormat.getNumberInstance();
        floor.setMaximumFractionDigits(0);
        floor.setRoundingMode(RoundingMode.FLOOR);

        NumberFormat ceiling = NumberFormat.getNumberInstance();
        ceiling.setMaximumFractionDigits(0);
        ceiling.setRoundingMode(RoundingMode.CEILING);

        if(stat == -1 && iv != -1 && ev != -1){
            stat = Integer.parseInt(floor.format((((2*base + iv + Integer.parseInt(floor.format(ev/4))) * level) / 100 + 5) * nature)); //CASE CALCULATE FOR STAT
        } else if(iv == -1 && stat != -1 && ev != -1){
            iv = ((Integer.parseInt(ceiling.format(stat/nature)) - 5) * 100) / level - 2*base - Integer.parseInt(floor.format(ev/4));     //CASE CALCULATE FOR IV
        } else if(ev == -1 && stat != -1 && iv != -1){
            ev = (((Integer.parseInt(ceiling.format(stat/nature))- 5) * 100) / level - 2*base - iv) * 4; //CASE CALCULATE FOR EV
        }
        return new int[] {(int) Math.round(stat), (int) Math.round(iv), (int) Math.round(ev)};
    }

    private void showDialog(String info, boolean haveDontShowThisAgain){
        AlertDialog.Builder builder = new AlertDialog.Builder(IVCalculatorActivity.this);
        builder.setMessage(info);
        if(haveDontShowThisAgain) {
            LayoutInflater adbInflater = LayoutInflater.from(IVCalculatorActivity.this);
            View view = adbInflater.inflate(R.layout.dialog_iv_calculator_warning, null);
            CheckBox dontShowAgain = view.findViewById(R.id.iv_calculator_dont_show_checkbox);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(compoundButton.isChecked()){
                        storeDialogStatus(true);
                    } else {
                        storeDialogStatus(false);
                    }
                }
            });
            if(!getDialogStatus()){
                builder.show();
            }
        } else {
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        }
    }

    private void storeDialogStatus(boolean isChecked){
        SharedPreferences mSharedPreferences = getSharedPreferences("CheckItem", MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean("iv_cal_dont_show", isChecked);
        mEditor.apply();
    }

    private boolean getDialogStatus(){
        SharedPreferences mSharedPreferences = getSharedPreferences("CheckItem", MODE_PRIVATE);
        return mSharedPreferences.getBoolean("iv_cal_dont_show", false);
    }
}
