package com.example.freshup.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freshup.Fragments.AppointFragment;
import com.example.freshup.Fragments.CartFragment;
import com.example.freshup.Fragments.HomeFragment;
import com.example.freshup.Fragments.OrdersFragment;
import com.example.freshup.R;

public class NavigatorActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    int check_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigator);
        check_value=getIntent().getIntExtra("check",0);



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

                    case R.id.nav_cart:
                        SelectFragment(new CartFragment());
//                        selectedFragment=new Tab2();
                        break;

                    case R.id.nav_orders:
                        SelectFragment(new OrdersFragment());
//                        selectedFragment=new Tab3();
                        break;

                    case R.id.nav_appoint:
                        SelectFragment(new AppointFragment());
//                        selectedFragment=new Tab3();
                        break;
                }

                return true;
            }
        });

        if (check_value==0){
            SelectFragment(new HomeFragment());
        }else if (check_value==1){
            navigationView.setSelectedItemId(R.id.nav_cart);
//            SelectFragment(new CartFragment());
        }



    }
    private void SelectFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
