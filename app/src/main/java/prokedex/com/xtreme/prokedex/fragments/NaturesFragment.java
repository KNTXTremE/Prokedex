package prokedex.com.xtreme.prokedex.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.customAdapters.NatureListAdapter;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Nature;

public class NaturesFragment extends Fragment {
    private RecyclerView recyclerNatureView;
    private ArrayList<Nature> allNature;
    private NatureListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_natures, null);

        recyclerNatureView = view.findViewById((R.id.nature_list_recycle));
        recyclerNatureView.setHasFixedSize(true);
        recyclerNatureView.setNestedScrollingEnabled(true);
        recyclerNatureView.setItemViewCacheSize(5);
        recyclerNatureView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerNatureView.setItemAnimator(new DefaultItemAnimator());
        allNature = getData();
        adapter = new NatureListAdapter(getContext(), allNature);

        recyclerNatureView.setAdapter(adapter);

        return view;
    }

    private ArrayList<Nature> getData() {
        ArrayList<Nature> allNatures = new ArrayList<>();
        for(int i = 1; i <= AllItems.getNatures().size(); i++){
            Nature nature = new Nature(AllItems.getNatures().get(i)[0], AllItems.getNatures().get(i)[1], AllItems.getNatures().get(i)[2], AllItems.getNatures().get(i)[1], AllItems.getNatures().get(i)[2]);
            allNatures.add(nature);
        }
        return allNatures;
    }
}
