package com.example.yoant.foodcritic.adapters.pager_adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.yoant.foodcritic.helper.constants.DayName;
import com.example.yoant.foodcritic.view.fragments.MenuFragment;

public class MenuPagerAdapter extends FragmentPagerAdapter {
    private static final int COUNT = 7;

    public MenuPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MenuFragment.newInstance(DayName.MONDAY);
            case 1:
                return MenuFragment.newInstance(DayName.TUESDAY);
            case 2:
                return MenuFragment.newInstance(DayName.WEDNESDAY);
            case 3:
                return MenuFragment.newInstance(DayName.THURSDAY);
            case 4:
                return MenuFragment.newInstance(DayName.FRIDAY);
            case 5:
                return MenuFragment.newInstance(DayName.SATURDAY);
            default:
                return MenuFragment.newInstance(DayName.SUNDAY);

        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return DayName.MONDAY;
            case 1:
                return DayName.TUESDAY;
            case 2:
                return DayName.WEDNESDAY;
            case 3:
                return DayName.THURSDAY;
            case 4:
                return DayName.FRIDAY;
            case 5:
                return DayName.SATURDAY;
            case 6:
                return DayName.SUNDAY;
            default:
                return null;
        }
    }
}
