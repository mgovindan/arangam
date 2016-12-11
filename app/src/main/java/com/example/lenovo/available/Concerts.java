package com.example.lenovo.available;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.lenovo.available.model.Venues;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 12/3/2016.
 */
public class Concerts extends Fragment {

    private RecyclerView recyclerView;
    Context context;
    private List<Artists> data;
    private CustomAdapterSchedule customAdapter;
    // private Activity active;
    public static final String PREFS_NAME = "AOP_PREFS";

    private ArrayList<Segment> ConcertList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.october, container, false);
        ///  this.active=aa;
        String str = loadData("segment_json");
//        customAdapter = new CustomAdapterSchedule(ConcertList,getContext());

        if (str.equals("def")) {

            new getDataTest(new JsonCallBack() {
                @Override
                public void success(String response) {
                    saveData(response);
                    parseJson(response);
                }

                @Override
                public void fail() {

                }
            }).execute("http://173.255.238.139:3030/api/1.0/segments");

        } else {
            parseJson(str);
        }
        recyclerView = (RecyclerView) v.findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(customAdapter);
        recyclerView.setBottom(customAdapter.getItemCount());

        return v;
    }

    public void parseJson(String response) {
        try {

            JSONObject jsonObj = new JSONObject(response);

            // Getting JSON Array node
            JSONArray data = jsonObj.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                Segment s = new Segment();

                s.setSegmentDate(obj.getString("segment_date"));
                s.setSegmentTime(obj.getString("segment_time"));
                String vid = (obj.getString("VenueId"));
                s.setVenueID(vid);
                String vname = getVenueName(vid);
                s.setVenueName(vname);
                s.setArtistID(obj.getString("ArtistId"));
                s.setId(obj.getString("id"));
                s.setAccompanists(obj.getString("accompanists"));
                s.setSegId(obj.getString("SegmentTypeId"));
                if (s.getSegId().equals("1") || s.getSegId().equals("5") || s.getSegId().equals("6")) {
                    ConcertList.add(s);

                }

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        customAdapter.notifyDataSetChanged();


    }

    private String getVenueName(String vid) {
        String venueName = "";
        try {
            String venueJson = loadData("venue_json");
            JSONObject j = new JSONObject(venueJson);
            JSONArray data = j.getJSONArray("data");
            for (int i = 0; i < data.length(); i++) {
                JSONObject obj = data.getJSONObject(i);
                Venues v = new Venues();
                if (obj.getString("id").equals(vid)) {
                    venueName = obj.getString("name");
                    //Toast.makeText(getActivity(),venueName,Toast.LENGTH_LONG).show();
                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return venueName;
    }


    private String loadData(String filename) {
        context = getActivity();
        SharedPreferences sp = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
        String response = sp.getString("response", "def");
        return response;
    }

    private void saveData(String response) {
        context = getActivity();
        SharedPreferences sharedPreferences = context.getSharedPreferences("segment_json", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("response", response);
        editor.commit();
    }

   /* public void upDateRecyclerView(ArrayList<Segment> data) {
//        if (ConcertList != null) {
//            this.ConcertList = ConcertList;
//            customAdapter.notifyDataSetChanged();
//            Log.e(TAG, "upDateRecyclerView: " + ConcertList.toString());
//        }

        customAdapter.setListUpdate(data);

        Toast.makeText(getContext(), "Se :" + ConcertList.toString(), Toast.LENGTH_SHORT).show();
    }*/


}




