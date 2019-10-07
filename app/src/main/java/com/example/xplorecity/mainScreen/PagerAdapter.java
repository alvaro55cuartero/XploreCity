package com.example.xplorecity.mainScreen;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.xplorecity.mainScreen.Tab1;
import com.example.xplorecity.mainScreen.Tab2;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs){
        super(fm);
        this.numOfTabs = numOfTabs;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){

            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;

            default: return null;

        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
