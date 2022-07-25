package com.example.myapplication.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.StudyModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StudyAdapter extends RecyclerView.Adapter<StudyAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<StudyModel> studyModels;

    public StudyAdapter(Context ctx, ArrayList<StudyModel> studyModels) {
        inflater = LayoutInflater.from(ctx);
        this.studyModels = studyModels;
    }

    @NonNull
    @Override
    public StudyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.studyarea, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudyAdapter.MyViewHolder holder, int position) {
//        final int radius = 5;
//        final int margin = 5;
//        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
//        Picasso.get().load(studyModels.get(position).getImage()).into(holder.iv);

//        final int radius = 5;
//        final int margin = 5;
//        final Transformation transformation = new RoundedCornersTransformation(radius, margin);
        Picasso.get().load(studyModels.get(position).getImage()).into(holder.iv);

//        Picasso.get()
//                .load(studyModels.get(position).getImage())
//                .memoryPolicy(MemoryPolicy.NO_CACHE)
//                .transform(RoundCornersTransform(32.0f))
//                .into(holder.iv);
        holder.name.setText(studyModels.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return studyModels.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);

//            country = (TextView) itemView.findViewById(R.id.country);
            name = (TextView) itemView.findViewById(R.id.text);
//            city = (TextView) itemView.findViewById(R.id.city);
            iv = (ImageView) itemView.findViewById(R.id.roundedImageView);
        }

    }
}
