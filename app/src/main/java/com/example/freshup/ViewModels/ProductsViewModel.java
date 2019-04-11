package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Models.GetHomeDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsViewModel extends ViewModel {
    private MutableLiveData<GetHomeDataModel> products;

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
}
