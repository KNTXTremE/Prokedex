package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.resources.Ability;

public class AbilityListAdapter extends RecyclerView.Adapter<AbilityListAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Ability> abilities;
    private Context context;

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Ability> abilitiesTemp;

    public AbilityListAdapter(Context context, ArrayList<Ability> abilities){
        inflater = LayoutInflater.from(context);
        this.abilities = abilities;
        this.context = context;
        this.abilitiesTemp = (ArrayList<Ability>) abilities.clone();

    }

    @Override
    public AbilityListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View abilityView = inflater.inflate(R.layout.lists_ability, viewGroup, false);
        return new AbilityListAdapter.MyViewHolder(abilityView);
    }

    @Override
    public void onBindViewHolder(final AbilityListAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.abilityName.setText(abilities.get(position).getName());
        viewHolder.abilityDesc.setText(abilities.get(position).getDescription());

        setAnimation(viewHolder.itemView, position);
    }

    public int getItemCount(){
        return abilities.size();
    }

    public void filter(String text) {
        if(!text.isEmpty()) {
            abilities.clear();
            for (Ability a : abilitiesTemp) {
                if (a.getName().toLowerCase().contains(text.toLowerCase())
                        || a.getDescription().toLowerCase().contains(text.toLowerCase())) {
                    abilities.add(a);
                }
            }
        } else{
            abilities = (ArrayList<Ability>) abilitiesTemp.clone();
        }
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        viewToAnimate.startAnimation(animation);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView abilityCardView;
        private TextView abilityName;
        private TextView abilityDesc;

        private MyViewHolder(View v) {
            super(v);
            this.abilityCardView = v.findViewById(R.id.cardview_abilitydex);
            this.abilityName = v.findViewById(R.id.ability_dex_name);
            this.abilityDesc = v.findViewById(R.id.ability_dex_desc);
        }
    }
}
