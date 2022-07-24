package com.example.myapplication.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Details;
import com.example.myapplication.R;
import com.example.myapplication.model.ListModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class ListAdapters extends RecyclerView.Adapter<ListAdapters.MyViewHolder> {
    private final LayoutInflater inflater;
    private final ArrayList<ListModel> listModelArrayList;
    public ListAdapters(Context ctx,ArrayList<ListModel> listModelArrayList){
        inflater=LayoutInflater.from(ctx);
        this.listModelArrayList=listModelArrayList;
    }
    @NonNull
    @Override
    public ListAdapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_three,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ListAdapters.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(listModelArrayList.get(position).getImgURL()).into(holder.iv);
        holder.name.setText(listModelArrayList.get(position).getCountry());
        holder.country.setText(listModelArrayList.get(position).getCity());
//        holder.itemView.getContext(listModelArrayList.get(position).getName().equals());
        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), Details.class);
                intent.putExtra("ArticleId",listModelArrayList.get(position).getName());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listModelArrayList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,country;
        ImageView iv,iv1;
        public MyViewHolder(View itemView) {
            super(itemView);
            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.name);
//            city = (TextView) itemView.findViewById(R.id.city);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            iv1=(ImageView) itemView.findViewById(R.id.iv1);

        }
    }
}