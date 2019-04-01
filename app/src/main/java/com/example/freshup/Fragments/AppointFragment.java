package com.example.freshup.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshup.Adapters.AppointPagerAdapter;
import com.example.freshup.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppointFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;


    public AppointFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_appoint, container, false);
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.vp);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.addTab(tabLayout.newTab().setText("Past"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final AppointPagerAdapter myadapter = new AppointPagerAdapter(getChildFragmentManager(), getActivity(), tabLayout.getTabCount());
        viewPager.setAdapter(myadapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}
