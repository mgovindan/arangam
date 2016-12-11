package com.example.lenovo.available.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//import com.amulyakhare.textdrawable.TextDrawable;
//import com.amulyakhare.textdrawable.util.ColorGenerator;

import com.example.lenovo.available.R;
import com.example.lenovo.available.model.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by Lenovo on 12/2/2016.
 */

public class CustomAdapterSchedule extends RecyclerView.Adapter<CustomAdapterSchedule.MyViewHolder> {

    public interface ActionListener{
        public void onFavorite(Segment segment);
        public void onUnFavorite(Segment segment);
        public void onUber(Segment segment);
        public void onBook();
    }

    private ActionListener listener;
    private List<Segment> dataSet = new ArrayList<>();
    int color = Color.parseColor("#F5F5F5");
    int color1 = Color.parseColor("#CD333B");
    //private Activity activity;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView venue;
        TextView accompanists;
        TextView segmentDateTime;
        ImageView favourite;
        ImageView uber;

        // public View testview;

        public MyViewHolder(View itemView) {
            super(itemView);
            //    testview=itemView;
            this.venue = (TextView) itemView.findViewById(R.id.venue);
            this.accompanists = (TextView) itemView.findViewById(R.id.accompanists);
            this.segmentDateTime = (TextView) itemView.findViewById(R.id.dateTime);
            //this.imageView=(ImageView)itemView.findViewById(R.id.icon);
            this.favourite = (ImageView) itemView.findViewById(R.id.favourite);
            //this.isPressed=(Boolean)itemView.findViewById(R.id.imageView3);
            this.uber=(ImageView)itemView.findViewById(R.id.uber);

        }
    }


    public CustomAdapterSchedule(List<Segment> data,ActionListener listener) {
        this.dataSet = data;
        this.listener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout_schedule, parent, false);

//        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final Segment segment = dataSet.get(listPosition);

        // String firstletter = Character.toString(dataModel.getName().charAt(0));
        //String color = "#CC955F";
        //TextDrawable drawable = TextDrawable.builder().buildRound(firstletter,Integer.parseInt(color));
        //ColorGenerator gen = ColorGenerator.MATERIAL;
        //int color = gen.
        holder.venue.setText(segment.getVenueName());
        holder.accompanists.setText(segment.getAccompanists());
        holder.segmentDateTime.setText(segment.getSegmentDate() + "   " + segment.getSegmentTime());

        // holder.textViewName.setTextColor(Integer.parseInt("#808080"));
        //      holder.imageViewprice.setText(dataModel.getPrice());
        // holder.imageViewprice.setTextColor(Integer.parseInt("#ADADAD"));
        //    holder.textViewdate.setText(dataModel.getDate());
        // holder.textViewdate.setTextColor(Integer.parseInt("#CECECE"));
        //holder.imageView.setImageDrawable(drawable);
        //this.imageView1=(ImageView)itemView.findViewById(R.id.imageView3);
        Log.e("Boolean status", "onBindViewHolder: " + segment.isFavorite());
        boolean a = segment.isFavorite();
        if (!a) {
            holder.favourite.setColorFilter(color);
        } else {
            holder.favourite.setColorFilter(color1);
        }
        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean a = segment.isFavorite();
                if (a) {
                    holder.favourite.setColorFilter(color);
                    segment.setIsFavorite(!a);
                    listener.onUnFavorite(segment);
                } else {
                    holder.favourite.setColorFilter(color1);
                    segment.setIsFavorite(!a);
                    listener.onFavorite(segment);
                }



            }
        });
      /*  if(b)
        {
            holder.imageView1.setColorFilter(color);
        }
        else {
            holder.imageView1.setColorFilter(color1);
        }*/
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}

