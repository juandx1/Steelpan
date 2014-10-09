package com.stigmasoft.matap.steelpan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Juan Manuel on 09/10/2014.
 */
public class SelectSectionAdapter extends FragmentPagerAdapter{

    public SelectSectionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new SongsIncompleteActivity();
            case 1:
                return new SongsCompleteActivity();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Songs";
            case 1:
                return "Completed";
        }
        return "";
    }
}
