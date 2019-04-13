package com.example.freshup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshup.Common;
import com.example.freshup.R;
import com.squareup.picasso.Picasso;

public class SubSubProductActivity extends AppCompatActivity {
    ImageView background,products_logo;
    TextView products_name;
    RecyclerView sub_sub_products_recycler;
    String category_id="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_product);
        background=findViewById(R.id.products_back_ground);
        products_logo=findViewById(R.id.logo_products);
        products_name=findViewById(R.id.name_products);
//        sub_sub_products_recycler=findViewById(R.id.sub_sub_products_recycler);
//        sub_sub_products_recycler.setLayoutManager(new GridLayoutManager(this,2));

        category_id=getIntent().getStringExtra("id");
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products background"+category_id)).into(background);
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products image"+category_id)).into(products_logo);
        products_name.setText(Common.GetToken(this,"products title"+category_id));





    }
}
