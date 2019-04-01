package com.example.freshup.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.freshup.Fragments.AllAppointmentsFragment;
import com.example.freshup.Fragments.PastAppointmentsFragment;
import com.example.freshup.Fragments.UpcomingAppointmentsFragment;

public class AppointPagerAdapter extends FragmentPagerAdapter {
    private Context mcontext;
    int t;

    public AppointPagerAdapter(FragmentManager fm, Context context, int t) {
        super(fm);
        mcontext = context;
        this.t=t;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                AllAppointmentsFragment allAppointmentsFragment = new AllAppointmentsFragment();
                return allAppointmentsFragment;
            case 1:
                UpcomingAppointmentsFragment upcomingAppointmentsFragment = new UpcomingAppointmentsFragment();
                return upcomingAppointmentsFragment;
            case 2:
                PastAppointmentsFragment pastAppointmentsFragment = new PastAppointmentsFragment();
                return pastAppointmentsFragment;

        }
        return  null;
    }

    @Override
    public int getCount() {
        return  t;
    }
}
