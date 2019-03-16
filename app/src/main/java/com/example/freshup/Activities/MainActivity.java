package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.freshup.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    this.sleep(3000);
                    Intent intent=new Intent(MainActivity.this, About.class);
                    startActivity(intent);
                } catch (InterruptedException e) {
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        };
        thread.start();
    }
}
