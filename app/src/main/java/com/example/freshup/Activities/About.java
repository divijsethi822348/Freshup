package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freshup.Adapters.PagerAdapter;
import com.example.freshup.R;

public class About extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        dots=findViewById(R.id.dots)
;
        viewPager=findViewById(R.id.viewpager);
        PagerAdapter adapter=new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
//        addDotsIndicator(1);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }


            @Override
            public void onPageSelected(int i) {
                addDotsIndicator(i);
                if (i==2){
                    Thread thread=new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                this.sleep(3000);
                                Intent intent=new Intent(About.this, LoginActivity.class);
                                startActivity(intent);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    thread.start();
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    private void addDotsIndicator(int position) {

        TextView[] tv_dots = new TextView[3];
        dots.removeAllViews();
        for (int i = 0; i < tv_dots.length; i++) {
            tv_dots[i] = new TextView(getApplicationContext());
            tv_dots[i].setText("•");
            tv_dots[i].setTextSize(35);
            tv_dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            dots.addView(tv_dots[i]);
        }

        tv_dots[position].setTextColor(getResources().getColor(R.color.colorAccent));
    }
}
