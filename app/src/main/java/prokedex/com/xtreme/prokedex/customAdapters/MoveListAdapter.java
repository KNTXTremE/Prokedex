package prokedex.com.xtreme.prokedex.customAdapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import prokedex.com.xtreme.prokedex.R;
import prokedex.com.xtreme.prokedex.resources.AllItems;
import prokedex.com.xtreme.prokedex.resources.Move;

public class MoveListAdapter extends RecyclerView.Adapter<MoveListAdapter.MyViewHolder> {
    private final LayoutInflater inflater;
    private ArrayList<Move> moves;
    private Context context;

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Move> movesTemp;

    public MoveListAdapter(Context context, ArrayList<Move> moves){
        inflater = LayoutInflater.from(context);
        this.moves = moves;
        this.context = context;
        this.movesTemp = (ArrayList<Move>) moves.clone();

    }

    @Override
    public MoveListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = inflater.inflate(R.layout.move_lists, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MoveListAdapter.MyViewHolder viewHolder, final int position) {
        viewHolder.moveName.setText(moves.get(position).getName());
        viewHolder.moveType.setText(AllItems.getElements().get(moves.get(position).getType()));
        viewHolder.moveType.setBackgroundColor(Color.parseColor(AllItems.getElementsColor(moves.get(position).getType())));
        viewHolder.moveCategory.setText(AllItems.getMoveCategory()[moves.get(position).getCategory()]);
        viewHolder.moveCategory.setBackgroundColor(Color.parseColor(AllItems.getMoveCategoryColor(moves.get(position).getCategory())));
        viewHolder.movePower.setText(moves.get(position).getPower());
        viewHolder.moveAccuracy.setText(moves.get(position).getAccuracy());

//        viewHolder.moveCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d(TAG, "onClick: clicked on: " + moves.get(position));
//
//                Intent intent = new Intent(context, MoveDescActivity.class);
//                intent.putExtra("move_name", moves.get(position).getName());
//                intent.putExtra("move_type", moves.get(position).getType());
//                context.startActivity(intent);
//            }
//        });
    }

    public int getItemCount(){
        return moves.size();
    }

    public void filter(String text) {
        if(!text.isEmpty()) {
            moves.clear();
            for (Move m : movesTemp) {
                if (m.getName().toLowerCase().contains(text.toLowerCase())
                        || AllItems.getElements().get(m.getType()).toLowerCase().contains(text.toLowerCase())
                        || AllItems.getMoveCategory()[m.getCategory()].toLowerCase().contains(text.toLowerCase())
                        || m.getPower().toLowerCase().contains(text.toLowerCase())
                        || m.getAccuracy().toLowerCase().contains(text.toLowerCase())) {
                    moves.add(m);
                }
            }
        } else{
            moves = (ArrayList<Move>) movesTemp.clone();
        }
        notifyDataSetChanged();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView moveCardView;
        private TextView moveName;
        private TextView moveType;
        private TextView moveCategory;
        private TextView movePower;
        private TextView moveAccuracy;

        private MyViewHolder(View v) {
            super(v);
            this.moveCardView = v.findViewById(R.id.cardview_movedex);
            this.moveName = v.findViewById(R.id.move_name);
            this.moveType = v.findViewById(R.id.move_type);
            this.moveCategory = v.findViewById(R.id.move_category);
            this.movePower = v.findViewById(R.id.move_power);
            this.moveAccuracy = v.findViewById(R.id.move_accuracy);
        }


    }
}
