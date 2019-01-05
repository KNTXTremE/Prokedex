package prokedex.com.xtreme.prokedex.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import prokedex.com.xtreme.prokedex.AboutActivity;
import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.SettingsActivity;

public class MoreFragment extends Fragment {
    private static final String TAG = "MoreFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        ImageView iVCalculatorImg = view.findViewById(R.id.more_iv_calculator_image);
        ImageView teamBuilderImg = view.findViewById(R.id.more_team_builder_image);
        ImageView abilityDexImg = view.findViewById(R.id.more_ability_dex_image);
        ImageView typeDexImg = view.findViewById(R.id.more_type_dex_image);
        ImageView settingsImg = view.findViewById(R.id.more_settings_image);
        ImageView aboutImg = view.findViewById(R.id.more_about_image);

        if(AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            iVCalculatorImg.setBackgroundResource(R.drawable.ic_more_iv_calculator_white);
            teamBuilderImg.setBackgroundResource(R.drawable.ic_build_white_24dp);
            abilityDexImg.setBackgroundResource(R.drawable.ic_more_ability_dex_white);
            typeDexImg.setBackgroundResource(R.drawable.ic_more_type_dex_white);
            settingsImg.setBackgroundResource(R.drawable.ic_settings_white_24dp);
            aboutImg.setBackgroundResource(R.drawable.ic_info_outline_white_24dp);
        } else {
            iVCalculatorImg.setBackgroundResource(R.drawable.ic_more_iv_calculator);
            teamBuilderImg.setBackgroundResource(R.drawable.ic_build_black_24dp);
            abilityDexImg.setBackgroundResource(R.drawable.ic_more_ability_dex);
            typeDexImg.setBackgroundResource(R.drawable.ic_more_type_dex);
            settingsImg.setBackgroundResource(R.drawable.ic_settings_black_24dp);
            aboutImg.setBackgroundResource(R.drawable.ic_info_outline_black_24dp);
        }

        CardView settings = view.findViewById(R.id.more_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Going to Settings Pref Activity");
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        CardView about = view.findViewById(R.id.more_about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Going to About Activity");
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
