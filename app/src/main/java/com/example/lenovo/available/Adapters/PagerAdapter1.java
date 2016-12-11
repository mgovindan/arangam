package com.example.lenovo.available.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.lenovo.available.DanceDrama;
import com.example.lenovo.available.LecDem;
import com.example.lenovo.available.Concerts;
import com.example.lenovo.available.SegmentsFragment;
import com.example.lenovo.available.model.Segment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PagerAdapter1 extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    private List<Segment> mConcertList;


    private List<Segment> mLecDemList;
    private List<Segment> mDanceDrama;

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;


    public PagerAdapter1(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return SegmentsFragment.newInstance(mConcertList);
            case 1:
                return SegmentsFragment.newInstance(mLecDemList);
            case 2:
                return SegmentsFragment.newInstance(mDanceDrama);
            default:
                return null;
        }

    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public void setConcertList(List<Segment> mConcertList) {
        this.mConcertList = mConcertList;
        if (this.mConcertList == null) {
            mConcertList = new ArrayList<>();
        }
    }

    public void setLecDemList(List<Segment> mLecDemList) {
        this.mLecDemList = mLecDemList;
        if (this.mLecDemList == null) {
            this.mLecDemList = new ArrayList<>();
        }
    }

    public void setDanceDrama(List<Segment> mDanceDrama) {
        this.mDanceDrama = mDanceDrama;
        if (this.mDanceDrama == null) {
            this.mDanceDrama = new ArrayList<>();
        }
    }
}