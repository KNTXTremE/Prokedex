package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Nature;

public class NatureListAdapter extends RecyclerView.Adapter<NatureListAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Nature> natures;
    private Context context;

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Nature> naturesTemp;

    public NatureListAdapter(Context context, ArrayList<Nature> natures){
        inflater = LayoutInflater.from(context);
        this.natures = natures;
        this.context = context;
        this.naturesTemp = (ArrayList<Nature>) natures.clone();

    }

    @Override
    public NatureListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.nature_list, viewGroup, false);
        return new NatureListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final NatureListAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.natureName.setText(natures.get(position).getName());
        viewHolder.natureIncStat.setText(AllItems.getStat(Integer.parseInt(natures.get(position).getIncStat())));
        viewHolder.natureDecStat.setText(AllItems.getStat(Integer.parseInt(natures.get(position).getDecStat())));
        viewHolder.natureFavFlavor.setText(AllItems.getFlavor(Integer.parseInt(natures.get(position).getFavFlavor())));
//        viewHolder.natureFavFlavor.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(moves.get(position).getType())));
        viewHolder.natureDisFlavor.setText(AllItems.getFlavor(Integer.parseInt(natures.get(position).getDisFlavor())));
//        viewHolder.natureDisFlavor.setBackgroundColor(Color.parseColor(AllItems.getMoveCategoryColor(moves.get(position).getCategory())));

        setAnimation(viewHolder.itemView, position);
    }

    public int getItemCount(){
        return natures.size();
    }

    public void filter(String text) {
        if(!text.isEmpty()) {
            natures.clear();
            for (Nature n : naturesTemp) {
                if (n.getName().toLowerCase().contains(text.toLowerCase())
                        || AllItems.getStat(Integer.parseInt(n.getIncStat())).toLowerCase().contains(text.toLowerCase())
                        || AllItems.getStat(Integer.parseInt(n.getDecStat())).toLowerCase().contains(text.toLowerCase())
                        || AllItems.getStat(Integer.parseInt(n.getFavFlavor())).toLowerCase().contains(text.toLowerCase())
                        || AllItems.getStat(Integer.parseInt(n.getDisFlavor())).toLowerCase().contains(text.toLowerCase())) {
                    natures.add(n);
                }
            }
        } else{
            natures = (ArrayList<Nature>) naturesTemp.clone();
        }
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        viewToAnimate.startAnimation(animation);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView natureCardView;
        private TextView natureName;
        private TextView natureIncStat;
        private TextView natureDecStat;
        private TextView natureFavFlavor;
        private TextView natureDisFlavor;

        private MyViewHolder(View v) {
            super(v);
            this.natureCardView = v.findViewById(R.id.cardview_nature);
            this.natureName = v.findViewById(R.id.nature_name);
            this.natureIncStat = v.findViewById(R.id.nature_inc_stat);
            this.natureDecStat = v.findViewById(R.id.nature_dec_stat);
            this.natureFavFlavor = v.findViewById(R.id.nature_fav_flavor);
            this.natureDisFlavor = v.findViewById(R.id.nature_dis_flavor);
        }


    }
}
