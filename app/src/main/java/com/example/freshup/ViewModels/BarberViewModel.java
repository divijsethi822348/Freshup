package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Models.BarberDetailsModel;
import com.example.freshup.Retrofit.Api;
import com.example.freshup.Retrofit.ApiClient;
import com.example.freshup.Util.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarberViewModel extends ViewModel {

    private MutableLiveData<BarberDetailsModel> barber;

    public LiveData<BarberDetailsModel> barber(final Activity activity, String apointmentDate){
        barber=new MutableLiveData<>();
        CommonUtils.showProgress(activity);
        Api api= ApiClient.getApiClient().create(Api.class);
        api.barbarDetail(apointmentDate).enqueue(new Callback<BarberDetailsModel>() {
            @Override
            public void onResponse(Call<BarberDetailsModel> call, Response<BarberDetailsModel> response) {
                CommonUtils.dismiss();
                if (response.body()!=null){
                    barber.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BarberDetailsModel> call, Throwable t) {
                CommonUtils.dismiss();
                Toast.makeText(activity, "Error : "+t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        return barber;
    }

}
