package com.example.lenovo.available;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.available.Adapters.CustomAdapterSchedule;
import com.example.lenovo.available.model.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FavouriteFragment extends Fragment implements CustomAdapterSchedule.ActionListener {
    private RecyclerView recyclerView;
    Context context;
    private CustomAdapterSchedule customAdapter;
    private List<Segment> favouriteList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.october, container, false);
        favouriteList=new ArrayList<>();
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle);
        String str = getFavoutiteSP("favorite_segments");
        favouriteList = toList(str);
        customAdapter = new CustomAdapterSchedule(favouriteList,this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setBottom(customAdapter.getItemCount());
        return v;

    }

    private String getFavoutiteSP(String value) {
            context=getActivity();
            SharedPreferences sp = context.getSharedPreferences(value, Context.MODE_PRIVATE);
            String response = sp.getString("response", " ");
        //Toast.makeText(getActivity(),response,Toast.LENGTH_LONG).show();
            return response;
        }
    private List<Segment> toList(String jsonString) {
        List<Segment> segmentList = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(jsonString);
            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                Segment s = new Segment();

                    s.setSegmentDate(obj.getString("segmentDate"));
                    s.setSegmentTime(obj.getString("segmentTime"));
                    s.setVenueID(obj.getString("venueID"));
                    s.setVenueName(obj.getString("venueName"));
                    s.setArtistID(obj.getString("artistID"));
                    s.setId(obj.getString("id"));
                    s.setAccompanists(obj.getString("Accompanists"));
                    s.setSegId(obj.getString("segId"));
                    favouriteList.add(s);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return favouriteList;
    }

    @Override
    public void onFavorite(Segment segment) {

    }

    @Override
    public void onUnFavorite(Segment segment) {


    }

    @Override
    public void onUber(Segment segment) {

    }

    @Override
    public void onBook() {

    }
}
