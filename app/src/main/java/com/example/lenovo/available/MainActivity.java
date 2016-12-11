package com.example.lenovo.available;


import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo.available.Adapters.CustomAdapterSchedule;
import com.example.lenovo.available.model.Venues;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    Context context;
    private ViewPager mViewPager;
    public ArrayList<Venues> VenueList= new ArrayList<>();

    private ImageView calendar, ticket, profile,artists,favourites;
    CustomAdapterSchedule customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        calendar = (ImageView) findViewById(R.id.calendar);
        //music = (ImageView) findViewById(R.id.music);
        favourites = (ImageView)findViewById(R.id.favourites);
        ticket = (ImageView) findViewById(R.id.ticket);
        artists = (ImageView)findViewById(R.id.artists);
        profile = (ImageView) findViewById(R.id.profile);

        calendar.setOnClickListener(this);
        favourites.setOnClickListener(this);
        ticket.setOnClickListener(this);
        artists.setOnClickListener(this);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                profile.setColorFilter(getResources().getColor(R.color.buttonPress));
                startActivity(intent);
            }
        });

        callFragment("Tickets");
    }

    private void callFragment(String value) {
        Fragment fragment = null;

//        if (value.equalsIgnoreCase("Tickets")||value.equalsIgnoreCase("Calendar")||value.equalsIgnoreCase("Music")||value.equalsIgnoreCase("Profile")){
//            fragment = new Tickets();
//        }
        favourites.setColorFilter(getResources().getColor(R.color.buttonUnPress));
        profile.setColorFilter(getResources().getColor(R.color.buttonUnPress));
        ticket.setColorFilter(getResources().getColor(R.color.buttonUnPress));
        artists.setColorFilter(getResources().getColor(R.color.buttonUnPress));
        calendar.setColorFilter(getResources().getColor(R.color.buttonUnPress));
        switch (value) {
            case "Tickets":
                fragment = new Tickets();
                ticket.setColorFilter(getResources().getColor(R.color.buttonPress));

                break;
            case "Schedule":
                fragment = new ScheduleFragment();
                calendar.setColorFilter(getResources().getColor(R.color.buttonPress));
                break;
            case "Favourites":
                fragment = new FavouriteFragment();
                break;
            case "Artists":
                fragment= new ArtistFragment();
                artists.setColorFilter(getResources().getColor(R.color.buttonPress));
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(value.toString());
        }

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.calendar:
                callFragment("Schedule");
                break;
            case R.id.ticket:
                callFragment("Tickets");
                break;
            case R.id.artists:
                callFragment("Artists");
                break;
            case R.id.favourites:
                callFragment("Favourites");
                break;
        }

    }




//    /**
//     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
//     * one of the sections/tabs/pages.
//     */
//    public class SectionsPagerAdapter extends FragmentPagerAdapter {
//
//        public SectionsPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            // getItem is called to instantiate the fragment for the given page.
//            // Return a PlaceholderFragment (defined as a static inner class below).
//            switch (position) {
//                case 0:
//                    available avail = new available();
//                    return avail;
//                case 1:
//                    booked book = new booked();
//                    return book;
//                default:
//                    return null;
//            }
//            //return PlaceholderFragment.newInstance(position + 1);
//        }
//
//        @Override
//        public int getCount() {
//            // Show 3 total pages.
//            return 2;
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//
//                    return "AVAILABLE";
//                case 1:
//
//                    return "BOOKED";
//                default:
//                    return null;
//            }
//            //return null;
//        }
//    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//        /**
//         * The fragment argument representing the section number for this
//         * fragment.
//         */
//        private static final String ARG_SECTION_NUMBER = "section_number";
//
//        /**
//         * Returns a new instance of this fragment for the given section
//         * number.
//         */
//        public static PlaceholderFragment newInstance(int sectionNumber) {
//            PlaceholderFragment fragment = new PlaceholderFragment();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragment.setArguments(args);
//            return fragment;
//        }
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.textView);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
//            return rootView;
//        }
//    }
}
