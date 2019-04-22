package com.example.freshup.Activities;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Adapters.SubSubProductsRecyclerAdapter;
import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.R;
import com.example.freshup.Util.App;
import com.example.freshup.ViewModels.CartViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubSubProductActivity extends AppCompatActivity {
    ImageView background,products_logo;
    TextView products_name;
    RecyclerView sub_sub_products_recycler;
    String category_id="";
    CartViewModel viewModel;
    List<SingleProductCategoryModel.Product> subSubProductList=new ArrayList<>();
    int quantity=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_sub_product);
        viewModel= ViewModelProviders.of(this).get(CartViewModel.class);
        background=findViewById(R.id.products_back_ground);
        products_logo=findViewById(R.id.logo_products);
        products_name=findViewById(R.id.name_products);
        sub_sub_products_recycler=findViewById(R.id.sub_sub_products_recycler);
        sub_sub_products_recycler.setLayoutManager(new GridLayoutManager(this,2));

        category_id=getIntent().getStringExtra("id");
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products background"+category_id)).into(background);
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products image"+category_id)).into(products_logo);
        products_name.setText(Common.GetToken(this,"products title"+category_id));

        subSubProductList= App.getSingleton().getSubSubProducts();

        sub_sub_products_recycler.setAdapter(new SubSubProductsRecyclerAdapter(this, subSubProductList, new SubSubProductsRecyclerAdapter.Quantity_Change() {
            @Override
            public void newQuantity(int number) {
                quantity=number;
            }
        }, new SubSubProductsRecyclerAdapter.Add_To_Cart_Click() {
            @Override
            public void choose(int position) {
                String userId=Common.GetToken(SubSubProductActivity.this,"ID");
                String productId=App.getSingleton().getProductId();

                viewModel.addToCartItems(SubSubProductActivity.this,userId,productId,quantity)
                        .observe(SubSubProductActivity.this, new Observer<AddToCartModel>() {
                            @Override
                            public void onChanged(@Nullable AddToCartModel addToCartModel) {
                                Toast.makeText(SubSubProductActivity.this, "Total Price"+addToCartModel.getTotalPrice(), Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(SubSubProductActivity.this,NavigatorActivity.class);
                                intent.putExtra("check",1);
                                startActivity(intent);
                            }
                        });
            }
        },  new SubSubProductsRecyclerAdapter.GO_To_Cart_Click() {
            @Override
            public void choose() {
                Intent intent=new Intent(SubSubProductActivity.this,NavigatorActivity.class);
                intent.putExtra("check",1);
                startActivity(intent);

            }
        }));




    }
}
