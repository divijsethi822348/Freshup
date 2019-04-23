package com.example.freshup.Activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.freshup.Adapters.SubProductsRecyclerAdapter;
import com.example.freshup.SharedPrefrences.Common;
import com.example.freshup.Models.SingleProductCategoryModel;
import com.example.freshup.R;
import com.example.freshup.Util.App;
import com.example.freshup.Util.CommonUtils;
import com.example.freshup.ViewModels.ProductsViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SubProductsActivity extends AppCompatActivity {
    ProductsViewModel viewModel;
    String category_id="",user_id="";
    ImageView background,products_image;
    TextView products_title;
    RecyclerView sub_products_recycler;
    List<SingleProductCategoryModel> list=new ArrayList<>();
    List<SingleProductCategoryModel.Detail> detailList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_products);
        background=findViewById(R.id.products_background);
        products_image=findViewById(R.id.image_products);
        products_title=findViewById(R.id.title_products);
        sub_products_recycler=findViewById(R.id.sub_products_recycler);

        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        sub_products_recycler.setLayoutManager(layoutManager);

        category_id= App.getSingleton().getProduct_id();
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products background"+category_id)).into(background);
        Picasso.with(getApplicationContext()).load(Common.GetToken(this,"products image"+category_id)).into(products_image);
        products_title.setText(Common.GetToken(this,"products title"+category_id));
        viewModel= ViewModelProviders.of(this).get(ProductsViewModel.class);
        user_id=Common.GetToken(this,"ID");
        CommonUtils.showProgress(SubProductsActivity.this);
        viewModel.subProducts(this,category_id,user_id).observe(this, new Observer<SingleProductCategoryModel>() {
            @Override
            public void onChanged(@Nullable SingleProductCategoryModel singleProductCategoryModel) {
                if (singleProductCategoryModel.getDetails().isEmpty()){
                    CommonUtils.dismiss();
                    Toast.makeText(SubProductsActivity.this, "No data found ", Toast.LENGTH_SHORT).show();
                }else {

                    Toast.makeText(SubProductsActivity.this, "Successfully fetched", Toast.LENGTH_SHORT).show();

                    for (int i=0;i<singleProductCategoryModel.getDetails().size();i++){
                        SingleProductCategoryModel model=new SingleProductCategoryModel();
                        SingleProductCategoryModel.Detail detail=new SingleProductCategoryModel.Detail();
                        detail.setTitle(singleProductCategoryModel.getDetails().get(i).getTitle());
                        detail.setImage(singleProductCategoryModel.getDetails().get(i).getImage());
                        detail.setCategoryId(singleProductCategoryModel.getDetails().get(i).getCategoryId());
                        detail.setProduct(singleProductCategoryModel.getDetails().get(i).getProduct());
                        detailList.add(detail);
                        model.setDetails(detailList);
                        list.add(model);

                        sub_products_recycler.setAdapter(new SubProductsRecyclerAdapter(SubProductsActivity.this,list));

                    }

                    CommonUtils.dismiss();
                }
            }
        });
    }

}
