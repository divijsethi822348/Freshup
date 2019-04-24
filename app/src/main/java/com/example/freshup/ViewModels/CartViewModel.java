package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Models.AddToCartModel;
import com.example.freshup.Models.GetAddToCartListModel;
import com.example.freshup.Retrofit.Api;
import com.example.freshup.Retrofit.ApiClient;
import com.example.freshup.Util.CommonUtils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartViewModel extends ViewModel {
    private MutableLiveData<AddToCartModel> addToCart;
    private MutableLiveData<GetAddToCartListModel> getCart;
    private MutableLiveData<Map> delete;

    public LiveData<AddToCartModel> addToCartItems(final Activity activity,String userId,String productId,Integer quantity){
        addToCart=new MutableLiveData<>();
        CommonUtils.showProgress(activity);
        Api api= ApiClient.getApiClient().create(Api.class);
        api.addtocart(userId,productId,quantity).enqueue(new Callback<AddToCartModel>() {
            @Override
            public void onResponse(Call<AddToCartModel> call, Response<AddToCartModel> response) {
                CommonUtils.dismiss();
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    addToCart.setValue(response.body());
                }
                else {
                    Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartModel> call, Throwable t) {
                CommonUtils.dismiss();
                Toast.makeText(activity, "error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return addToCart;
    }

    public LiveData<GetAddToCartListModel> getCartData(final Activity activity, String userId){
        getCart=new MutableLiveData<>();
        CommonUtils.showProgress(activity);
        Api api=ApiClient.getApiClient().create(Api.class);
        api.getCartList(userId).enqueue(new Callback<GetAddToCartListModel>() {
            @Override
            public void onResponse(Call<GetAddToCartListModel> call, Response<GetAddToCartListModel> response) {
                CommonUtils.dismiss();

                    getCart.setValue(response.body());

            }

            @Override
            public void onFailure(Call<GetAddToCartListModel> call, Throwable t) {
                CommonUtils.dismiss();
                Toast.makeText(activity, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return getCart;
    }

    public LiveData<Map> delete(final Activity activity, String id){
        delete=new MutableLiveData<>();
        CommonUtils.showProgress(activity);
        Api api=ApiClient.getApiClient().create(Api.class);
        api.DeleteItems(id).enqueue(new Callback<Map>() {
            @Override
            public void onResponse(Call<Map> call, Response<Map> response) {
                CommonUtils.dismiss();
                delete.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Map> call, Throwable t) {
                CommonUtils.dismiss();
                Toast.makeText(activity, "failed"+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });


        return delete;
    }
}
