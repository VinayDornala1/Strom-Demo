package com.example.myapplication.adapter;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.PopularModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<PopularModel> images;
    public PopularAdapter(Context ctx, ArrayList<PopularModel> images){
        inflater=LayoutInflater.from(ctx);
        this.images=images;
    }
    @NonNull
    @Override
    public PopularAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.popularuniversity,parent,false);
        PopularAdapter.MyViewHolder holder= new PopularAdapter.MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.MyViewHolder holder, int position) {
        Picasso.get().load(images.get(position).getImages()).into(holder.iv);
        holder.textView.setText(images.get(position).getText());
        holder.textView1.setText(images.get(position).getText1());
        holder.textView2.setText(images.get(position).getText2());
//        holder.textView3.setPaintFlags(textView3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
//        holder.textView3.setText(images.get(position).getText3());
//        holder.textView4.setText(images.get(position).getText4());
//        Picasso.get().load(images.get(position).getImages()).into(holder.iv);
//        if (images.get(position).getText4().isEmpty()){
//            holder.textView5.setVisibility(View.GONE);
//        }else {
//        }
//        if(dataModelArrayListS.get(position).getImgURLS().equals("")){
//
//            Picasso.get().load(dataModelArrayListS.get(position).getImgURLS()).into(holder.iv);
//        }else {
//
//            Picasso.get().load(dataModelArrayListS.get(position).getImgUrl()).into(holder.iv);
//        }
    }
    @Override
    public int getItemCount() {
        return images.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textView,textView1,textView2,textView3,textView4,textView5;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            textView=itemView.findViewById(R.id.name);
            textView1=itemView.findViewById(R.id.country);
            textView2=itemView.findViewById(R.id.country1);
            textView3=itemView.findViewById(R.id.text);
            textView3.setPaintFlags(textView3.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);
        }
    }
}