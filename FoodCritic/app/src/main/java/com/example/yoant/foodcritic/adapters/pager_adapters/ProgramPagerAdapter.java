package com.example.yoant.foodcritic.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yoant.foodcritic.helper.constants.ProgramType;
import com.example.yoant.foodcritic.view.fragments.ProgramFragment;

public class ProgramPagerAdapter extends FragmentPagerAdapter {

    private static int NUMBER = 3;

    public ProgramPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProgramFragment.newInstance(ProgramType.general);
            case 1:
                return ProgramFragment.newInstance(ProgramType.favorite);
            default:
                return ProgramFragment.newInstance(ProgramType.rejected);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return ProgramType.general;
            case 1:
                return ProgramType.favorite;
            default:
                return ProgramType.rejected;
        }
    }

    @Override
    public int getCount() {
        return NUMBER;
    }
}
