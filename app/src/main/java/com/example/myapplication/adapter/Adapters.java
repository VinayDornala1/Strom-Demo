package com.example.myapplication.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Models;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class Adapters extends RecyclerView.Adapter<Adapters.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Models> image;
    public Adapters(Context ctx, ArrayList<Models> image){
        inflater=LayoutInflater.from(ctx);
        this.image=image;
    }




    @NonNull
    @Override
    public Adapters.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.rv_two,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull Adapters.MyViewHolder holder, int position) {
        holder.textView.setText(image.get(position).getName());
        holder.textView1.setText(image.get(position).getNames());
        if(image.get(position).getImgURLS().equals("")){

            Picasso.get().load(image.get(position).getImgURLS()).into(holder.iv);
        }else {

            Picasso.get().load(image.get(position).getImgUrl()).into(holder.iv);
        }
    }
    @Override
    public int getItemCount() {
        return image.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textView,textView1;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.name);
            textView1=itemView.findViewById(R.id.names);
        }
    }
}

