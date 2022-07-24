package com.example.myapplication.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.DataModels;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.MyViewHolder> {
    int selectedPosition=0;
    boolean isSelectMode = false;
    private LayoutInflater inflater;
    private ArrayList<DataModels> dataModelArrayList;
    private Context ctx;
    public ListsAdapter(Context ctx, ArrayList<DataModels> dataModelArrayList) {
        ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }
    @Override
    public ListsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ListsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final DataModels model = dataModelArrayList.get(position);
        Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.name.setText(dataModelArrayList.get(position).getName());
        holder.cardView.setBackgroundColor(model.isSelected()?Color.CYAN:Color.WHITE);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i=0;i<dataModelArrayList.size();i++){
                    if (dataModelArrayList.get(i).isSelected()==true){
                        Log.e("strrrrr",""+selectedPosition);
                        selectedPosition=selectedPosition+1;
                        Log.e("strrrrr",""+selectedPosition);
                    }
                }
                if (selectedPosition>2){
                    selectedPosition=0;
                }else{
                    selectedPosition=0;
                    holder.cardView.setBackgroundColor(model.isSelected()?Color.CYAN:Color.WHITE);
                    dataModelArrayList.get(position).setSelected(true);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView iv;
        CardView cardView;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}