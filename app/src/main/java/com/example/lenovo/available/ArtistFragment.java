package com.example.lenovo.available;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.lenovo.available.model.Artists;
import com.example.lenovo.available.model.Segment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by krishna on 12/10/16.
 */

public class ArtistFragment extends Fragment{
    List<String> artist_list;
    Context context;
    EditText searchArtist;
    ArrayAdapter<String> adapter;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.artist_list_layout, container, false);

        artist_list = new ArrayList<String>();
        searchArtist = (EditText) rootView.findViewById(R.id.searchArtist);
        searchArtist.setVisibility(View.GONE);
        String str = loadData("artist_json");
        if (str.equals("def")) {
            new getDataTest(new JsonCallBack() {
                @Override
                public void success(String response) {

                    // Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();
                    saveData(response);
                    parseArtist(response);

                }

                @Override
                public void fail() {

                    Toast.makeText(getActivity(), "failed", Toast.LENGTH_LONG).show();
                }
            }).execute("http://173.255.238.139:3030/api/1.0/artists");
        }
        else
        {
            parseArtist(str);
        }
        Set<String> hs = new HashSet<>();
        hs.addAll(artist_list);
        artist_list.clear();
        artist_list.addAll(hs);
        String[] mStringArray = new String[artist_list.size()];
        mStringArray = artist_list.toArray(mStringArray);
        Arrays.sort(mStringArray);
        final  ListView lv = (ListView)rootView.findViewById(R.id.artist_list_view);
        adapter=new ArrayAdapter<String>(getActivity(),R.layout.artist_row,R.id.txtitem,mStringArray);
        lv.setAdapter(adapter);
        setRetainInstance(true);

        searchArtist.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return rootView;

    }


    private void parseArtist(String response) {
        try {

            JSONObject jsonObj = new JSONObject(response);
            // Toast.makeText(getActivity(), jsonObj.toString(), Toast.LENGTH_LONG).show();

            JSONArray artists = jsonObj.getJSONArray("artists");

            for (int i = 0; i < artists.length(); i++) {
                JSONObject obj = artists.getJSONObject(i);
                Artists a = new Artists();
                a.setId(obj.getString("id"));
                a.setName(obj.getString("name"));
                artist_list.add(a.getName());
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_schedule,menu);

        final MenuItem item=menu.findItem(R.id.action_search);
        final SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private String loadData(String value) {
        context = getContext();
        SharedPreferences sp = context.getSharedPreferences(value, Context.MODE_PRIVATE);
        String response = sp.getString("response", "def");
        return response;
    }
    private void saveData(String response) {
        context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("artist_json", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("response", response);
        editor.commit();
    }


}