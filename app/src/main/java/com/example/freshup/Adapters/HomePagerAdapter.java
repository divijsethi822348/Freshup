package com.example.freshup.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.freshup.Fragments.ProductsFragment;
import com.example.freshup.Fragments.ServicesFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {
    public HomePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                ServicesFragment servicesFragment=new ServicesFragment();
                return servicesFragment;

            case 1:
                ProductsFragment productsFragment=new ProductsFragment();
                return productsFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
