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
import com.example.myapplication.model.ReadyModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
public class ReadyAdapter extends RecyclerView.Adapter<ReadyAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<ReadyModel> dataModelArrayListS;
    public ReadyAdapter(Context ctx, ArrayList<ReadyModel> dataModelArrayListS){
        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayListS=dataModelArrayListS;
    }
    @NonNull
    @Override
    public ReadyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.readyapply,parent,false);
        ReadyAdapter.MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull ReadyAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(dataModelArrayListS.get(position).getText());
        holder.textView1.setText(dataModelArrayListS.get(position).getText1());
        holder.textView2.setText(dataModelArrayListS.get(position).getText2());
        holder.textView3.setText(dataModelArrayListS.get(position).getText3());
        holder.textView4.setText(dataModelArrayListS.get(position).getText4());
        Picasso.get().load(dataModelArrayListS.get(position).getImages()).into(holder.iv);
        if (dataModelArrayListS.get(position).getText4().isEmpty()){
            holder.textView5.setVisibility(View.GONE);
        }else {
        }
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
        return dataModelArrayListS.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView textView,textView1,textView2,textView3,textView4,textView5;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.image);
            textView=itemView.findViewById(R.id.text);
            textView1=itemView.findViewById(R.id.text1);
            textView2=itemView.findViewById(R.id.text2);
            textView3=itemView.findViewById(R.id.text3);
            textView4=itemView.findViewById(R.id.text5);
            textView5=itemView.findViewById(R.id.text4);
        }
    }
}