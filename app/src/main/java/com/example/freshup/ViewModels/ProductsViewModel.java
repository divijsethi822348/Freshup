package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.Models.SingleProductCategoryModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<GetHomeDataModel> products;
    private MutableLiveData<SingleProductCategoryModel> subproducts;

    public LiveData<GetHomeDataModel> products(final Activity activity){
        products=new MutableLiveData<>();

        Api api= ApiClient.getApiClient().create(Api.class);
        api.getProduct().enqueue(new Callback<GetHomeDataModel>() {
            @Override
            public void onResponse(Call<GetHomeDataModel> call, Response<GetHomeDataModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    products.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetHomeDataModel> call, Throwable t) {
                Toast.makeText(activity, "Error loading products", Toast.LENGTH_SHORT).show();
            }
        });

        return products;
    }

    public LiveData<SingleProductCategoryModel> subProducts(final Activity activity,String category_id,String id){
        subproducts=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.getProducts(category_id,id).enqueue(new Callback<SingleProductCategoryModel>() {
            @Override
            public void onResponse(Call<SingleProductCategoryModel> call, Response<SingleProductCategoryModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    subproducts.setValue(response.body());
                }else if (response.body().getSuccess().equalsIgnoreCase("0")){
                    Toast.makeText(activity, "No details Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SingleProductCategoryModel> call, Throwable t) {
                Toast.makeText(activity, "error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        return subproducts;
    }
}
