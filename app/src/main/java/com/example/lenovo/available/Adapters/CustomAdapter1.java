package com.example.lenovo.available.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.available.DataModel;
import com.example.lenovo.available.R;

import java.util.ArrayList;

public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.MyViewHolder> {

    private ArrayList<DataModel> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewdate;
        TextView imageViewprice;
        ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.text1);
            this.textViewdate = (TextView) itemView.findViewById(R.id.text2);
            this.imageViewprice = (TextView) itemView.findViewById(R.id.text3);
            this.img=(ImageView)itemView.findViewById(R.id.icon);
        }
    }

    public CustomAdapter1(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout1, parent, false);



//        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        DataModel dataModel = dataSet.get(listPosition);
        holder.textViewName.setText(dataModel.getName());
       // holder.textViewName.setTextColor(Integer.parseInt("#808080"));
        holder.imageViewprice.setText(dataModel.getPrice());
       // holder.imageViewprice.setTextColor(Integer.parseInt("#ADADAD"));
        holder.textViewdate.setText(dataModel.getDate());
       // holder.textViewdate.setTextColor(Integer.parseInt("#CECECE"));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}