package prokedex.com.xtreme.prokedex.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import prokedex.com.xtreme.prokedex.R;

public class ItemdexFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_itemdex, null);
        setAnimation(view);
        return view;
    }

    private void setAnimation(View viewToAnimate)
    {
        Animation animation = AnimationUtils.loadAnimation(getContext(), android.R.anim.fade_in);
        viewToAnimate.startAnimation(animation);
    }
}
