package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.Model;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends SliderViewAdapter<MyAdapter.SliderAdapterViewHolder> /*RecyclerView.Adapter<MyAdapter.MyViewHolder>*/ {
//    private LayoutInflater inflater;
//    private ArrayList<Model> images;
//    public MyAdapter(Context ctx,ArrayList<Model> images){
//        inflater=LayoutInflater.from(ctx);
//        this.images=images;
//    }
//    @NonNull
//    @Override
//    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view=inflater.inflate(R.layout.rv_one,parent,false);
//        MyViewHolder holder=new MyViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
//        Picasso.get().load(images.get(position).getImgURL()).into(holder.iv);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return images.size();
//    }
//    class MyViewHolder extends RecyclerView.ViewHolder{
//
//        ImageView iv;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//
////            country = (TextView) itemView.findViewById(R.id.country);
////            name = (TextView) itemView.findViewById(R.id.name);
////            city = (TextView) itemView.findViewById(R.id.city);
//            iv = (ImageView) itemView.findViewById(R.id.iv);
//        }
//
//    }
private final List<Model> mSliderItems;

    // Constructor
    public MyAdapter(Context context, ArrayList<Model> sliderDataArrayList) {
        this.mSliderItems = sliderDataArrayList;
    }

    // We are inflating the slider_layout
    // inside on Create View Holder method.
    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_one, null);
        return new SliderAdapterViewHolder(inflate);
    }

    // Inside on bind view holder we will
    // set data to item of Slider View.
    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        final Model sliderItem = mSliderItems.get(position);

        // Glide is use to load image
        // from url in your imageview.
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImgURL())
                .fitCenter()
                .into(viewHolder.imageViewBackground);
    }

    // this method will return
    // the count of our list.
    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        // Adapter class for initializing
        // the views of our slider view.
        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv);
            this.itemView = itemView;
        }
    }
}
