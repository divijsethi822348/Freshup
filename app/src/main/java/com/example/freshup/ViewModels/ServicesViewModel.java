package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Retrofit.Api;
import com.example.freshup.Retrofit.ApiClient;
import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.Models.GetServicesDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ServicesViewModel extends ViewModel {
    private MutableLiveData<GetHomeDataModel> services;
    private MutableLiveData<GetServicesDataModel> subservices;

    public LiveData<GetHomeDataModel> services(final Activity activity){
        services=new MutableLiveData<>();

        Api api= ApiClient.getApiClient().create(Api.class);
        api.getServices().enqueue(new Callback<GetHomeDataModel>() {
            @Override
            public void onResponse(Call<GetHomeDataModel> call, Response<GetHomeDataModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    services.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetHomeDataModel> call, Throwable t) {
                Toast.makeText(activity, "Failed to load services", Toast.LENGTH_SHORT).show();
            }
        });


        return services;
    }

    public LiveData<GetServicesDataModel> subServices(final Activity activity,String id){
        subservices=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.getSubServices(id).enqueue(new Callback<GetServicesDataModel>() {
            @Override
            public void onResponse(Call<GetServicesDataModel> call, Response<GetServicesDataModel> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    subservices.setValue(response.body());
                }else {
                    Toast.makeText(activity, "Incorrect service id", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetServicesDataModel> call, Throwable t) {
                Toast.makeText(activity, "error :"+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return subservices;

    }
}
