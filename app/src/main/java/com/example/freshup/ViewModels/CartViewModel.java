package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Retrofit.Api;
import com.example.freshup.Retrofit.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartViewModel extends ViewModel {
    private MutableLiveData<AddToCartModel> addToCart;

    public LiveData<AddToCartModel> addToCartItems(final Activity activity,String userId,String productId,Integer quantity){
        addToCart=new MutableLiveData<>();

        Api api= ApiClient.getApiClient().create(Api.class);
        api.addtocart(userId,productId,quantity).enqueue(new Callback<AddToCartModel>() {
            @Override
            public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    addToCart.setValue(response.body());
                }
                else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartModel> call, Throwable t) {
                Toast.makeText(activity, "error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return addToCart;
    }
}
