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
import com.example.myapplication.model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<DataModel> dataModelArrayList;

    public ListAdapter(Context ctx, ArrayList<DataModel> dataModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }




    @NonNull
    @Override
    public ListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lv_player, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(dataModelArrayList.get(position).getCountry());
        holder.textView1.setText(dataModelArrayList.get(position).getCity());
        Picasso.get().load(dataModelArrayList.get(position).getImgUrl()).into(holder.iv);
//        if(image.get(position).getImgURLS().equals("")){
//
//        Picasso.get().load(image.get(position).getImgURLS()).into(holder.iv);
//        }else {
//
//        Picasso.get().load(image.get(position).getImgUrl()).into(holder.iv);
//        }
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView textView, textView1;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            textView = itemView.findViewById(R.id.name);
            textView1 = itemView.findViewById(R.id.country);
        }
    }
}
//        BaseAdapter {
//
//    private Context context;
//    private ArrayList<DataModel> dataModelArrayList;
//
//    public ListAdapter(Context context, ArrayList<DataModel> dataModelArrayList) {
//
//        this.context = context;
//        this.dataModelArrayList = dataModelArrayList;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return getCount();
//    }
//    @Override
//    public int getItemViewType(int position) {
//
//        return position;
//    }
//
//    @Override
//    public int getCount() {
//        return dataModelArrayList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return dataModelArrayList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @SuppressLint("SetTextI18n")
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//
//        if (convertView == null) {
//            holder = new ViewHolder();
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            convertView = inflater.inflate(R.layout.lv_player, null, true);
//
//            holder.iv = (ImageView) convertView.findViewById(R.id.iv);
//            holder.tvname = (TextView) convertView.findViewById(R.id.name);
//            holder.tvcountry = (TextView) convertView.findViewById(R.id.country);
////            holder.tvcity = (TextView) convertView.findViewById(R.id.city);
//
//            convertView.setTag(holder);
//        }else {
//            // the getTag returns the viewHolder object set as a tag to the view
//            holder = (ViewHolder)convertView.getTag();
//        }
//
//        Picasso.get().load(dataModelArrayList.get(position).getImgUrl()).into(holder.iv);
////        holder.tvname.setText("Name: "+dataModelArrayList.get(position).getName());
//        holder.tvname.setText("Country: "+dataModelArrayList.get(position).getCountry());
//        holder.tvcountry.setText("City: "+dataModelArrayList.get(position).getCity());
//
//        return convertView;
//    }
//
//    private class ViewHolder {
//
//        protected TextView tvname, tvcountry, tvcity;
//        protected ImageView iv;
//    }


