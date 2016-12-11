package com.example.lenovo.available;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo.available.Adapters.CustomAdapterSchedule;
import com.example.lenovo.available.model.Artists;
import com.example.lenovo.available.model.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SegmentsFragment extends Fragment implements CustomAdapterSchedule.ActionListener {


    private RecyclerView recyclerView;
    Context context;
    private List<Artists> data;
    private CustomAdapterSchedule customAdapter;
    // private Activity active;
    public static final String PREFS_NAME = "AOP_PREFS";

    private List<Segment> segmentList;

    public SegmentsFragment() {
        // Required empty public constructor
    }

    public static SegmentsFragment newInstance(List<Segment> segments) {

        SegmentsFragment fragment = new SegmentsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("segments", (Serializable) segments);
        fragment.setArguments(bundle);
        return fragment;
    }

    public List<Segment> getsegmentList() {
        if (segmentList == null) {
            segmentList = new ArrayList<>();
        }
        return segmentList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.october, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            segmentList = (List<Segment>) bundle.getSerializable("segments");
//            toList(bundle.getString("segment_list"));
        } else {
            segmentList = new ArrayList<>();
        }
        customAdapter = new CustomAdapterSchedule(segmentList, this);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setBottom(customAdapter.getItemCount());

        return v;
    }

    @Override
    public void onFavorite(Segment segment) {
        try {
            SharedPreferences preferences = getContext().getSharedPreferences("favorite_segments", Context.MODE_PRIVATE);
            String jsonResult = preferences.getString("response", " ");
            List<Segment> previousList = toList(jsonResult);
            segment.setIsFavorite(true);
            previousList.add(segment);
            preferences.edit()
                    .putString("response", new JSONArray(previousList.toString()).toString()).apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUnFavorite(Segment segment) {
        try {
            SharedPreferences preferences = getContext().getSharedPreferences("favorite_segments", Context.MODE_PRIVATE);
            String jsonResult = preferences.getString("response", " ");
            List<Segment> previousList = toList(jsonResult);
            int i=0;
            for(i=0;i<previousList.size();i++)
            {
                if(segment.getId().equals(previousList.get(i).getId()))
                {previousList.remove(i);
                    break;}

            }
            preferences.edit()
                    .putString("response", new JSONArray(previousList.toString()).toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUber(Segment segment) {

    }

    @Override
    public void onBook() {

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
                segmentList.add(s);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return segmentList;
    }
}
