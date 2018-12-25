package prokedex.com.xtreme.prokedex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;


public class MovedexFragment extends Fragment {

    private static final String TAG = "MovedexFragment";
    RecyclerView recyclerMoveView;
    ArrayList<Move> allMove;
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movedex, null);
        recyclerMoveView = view.findViewById((R.id.move_list_recycle));
        recyclerMoveView.setHasFixedSize(true);
        recyclerMoveView.setNestedScrollingEnabled(false);
        recyclerMoveView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerMoveView.setItemAnimator(new DefaultItemAnimator());
        allMove = getData();
        MoveListAdapter adapter = new MoveListAdapter(getContext(), allMove);

        recyclerMoveView.setAdapter(adapter);
        return view;
    }

    private ArrayList<Move> getData() {
        ArrayList<Move> allMove = new ArrayList<>();
        for(int i = 1; i <= AllItems.getMoves().size(); i++){
            Move move = new Move(AllItems.getMoves().get(i)[0], Integer.parseInt(AllItems.getMoves().get(i)[1]), Integer.parseInt(AllItems.getMoves().get(i)[2]), AllItems.getMoves().get(i)[3], AllItems.getMoves().get(i)[4]);
            allMove.add(move);
        }
        return allMove;
    }
}
