package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import prokedex.com.xtreme.prokedex.resources.Item;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Item> items;
    private Context context;

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Item> itemsTemp;

    public ItemListAdapter(Context context, ArrayList<Item> items){
        inflater = LayoutInflater.from(context);
        this.items = items;
        this.context = context;
        this.itemsTemp = (ArrayList<Item>) items.clone();

    }

    @Override
    public ItemListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.item_lists, viewGroup, false);
        return new ItemListAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemListAdapter.MyViewHolder viewHolder, final int position) {
        int image = items.get(position).getImage();
        Glide.with(context).load(image).into(viewHolder.itemImage);
        viewHolder.itemName.setText(items.get(position).getName());
        viewHolder.itemDesc.setText(items.get(position).getDescription());

        setAnimation(viewHolder.itemView, position);
    }

    public int getItemCount(){
        return items.size();
    }

    public void filter(String text) {
        if(!text.isEmpty()) {
            items.clear();
            for (Item i : itemsTemp) {
                if (i.getName().toLowerCase().contains(text.toLowerCase())
                        || i.getDescription().toLowerCase().contains(text.toLowerCase())) {
                    items.add(i);
                }
            }
        } else{
            items = (ArrayList<Item>) itemsTemp.clone();
        }
        notifyDataSetChanged();
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in);
        viewToAnimate.startAnimation(animation);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView itemCardView;
        private ImageView itemImage;
        private TextView itemName;
        private TextView itemDesc;

        private MyViewHolder(View v) {
            super(v);
            this.itemCardView = v.findViewById(R.id.cardview_itemdex);
            this.itemImage = v.findViewById(R.id.itemdex_image);
            this.itemName = v.findViewById(R.id.itemdex_item_name);
            this.itemDesc = v.findViewById(R.id.itemdex_item_desc);
        }


    }
}
