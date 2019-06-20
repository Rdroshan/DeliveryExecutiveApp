package com.example.adminpc.deliveryexecutiveapp;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by adminpc on 18-06-2019.
 */

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;
    public PageAdapter(FragmentManager fm, int numOftabs) {
        super(fm);
        this.numOfTabs = numOftabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new LoginFrag();
            case 1:
                return new RegisterFrag();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Sign In";
            case 1:
                return "Register";
        }
        return null;
    }
}
