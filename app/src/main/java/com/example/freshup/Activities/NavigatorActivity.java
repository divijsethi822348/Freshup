package com.example.freshup.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freshup.Fragments.HomeFragment;
import com.example.freshup.Fragments.ProfileFragment;
import com.example.freshup.Fragments.PromoFragment;
import com.example.freshup.R;
import com.example.freshup.Fragments.SettingsFragment;

public class NavigatorActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);

        SelectFragment(new HomeFragment());
        navigationView=findViewById(R.id.bottom_navigator);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment=null;

                switch (menuItem.getItemId()){
                    case R.id.nav_home:
//                        Tab1 tab1=new Tab1();
                        SelectFragment(new HomeFragment());
                        break;

                    case R.id.nav_promo:
                        SelectFragment(new PromoFragment());
//                        selectedFragment=new Tab2();
                        break;

                    case R.id.nav_profile:
                        SelectFragment(new ProfileFragment());
//                        selectedFragment=new Tab3();
                        break;

                    case R.id.nav_settings:
                        SelectFragment(new SettingsFragment());
//                        selectedFragment=new Tab3();
                        break;
                }

                return true;
            }
        });
    }
    private void SelectFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }
}
