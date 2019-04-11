package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.freshup.Common;
import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.R;
import com.example.freshup.ViewModels.ProductsViewModel;

public class VetementsActivity extends AppCompatActivity {
    ProductsViewModel viewModel;
    String category_id="",user_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vetements);

        viewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        category_id= Common.GetToken(this,"product id");
        user_id=Common.GetToken(this,"ID");

        viewModel.subProducts(this,category_id,user_id).observe(this, new Observer<SingleProductCategoryModel>() {
            @Override
            public void onChanged(@Nullable SingleProductCategoryModel singleProductCategoryModel) {
                Toast.makeText(VetementsActivity.this, "Successfully fetched", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(this,NavigatorActivity.class);
        startActivity(intent);
    }
}
