package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.Models.SimplePojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterModel> userRegister;
    private MutableLiveData<SimplePojo> verification;

    public LiveData<RegisterModel> userRegister(final Activity activity, String name, String email, String phone, String password, String device_type, final String reg_id){
        userRegister=new MutableLiveData<>();

        Api api= ApiClient.getApiClient().create(Api.class);
        api.UserRegister(name,email,phone,password,device_type,reg_id).enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    userRegister.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        return userRegister;
    }

    public LiveData<SimplePojo> verification(final Activity activity, String id, String otp){
        verification=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.matchToken(id,otp).enqueue(new Callback<SimplePojo>() {
            @Override
            public void onResponse(Call<SimplePojo> call, Response<SimplePojo> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    verification.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SimplePojo> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return verification;

    }
}
