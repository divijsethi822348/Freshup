package com.example.freshup.ViewModels;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import com.example.freshup.Api;
import com.example.freshup.ApiClient;
import com.example.freshup.Models.GetProfilePojo;
import com.example.freshup.Models.OtpPojo;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.Models.SimplePojo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Part;

public class UserRegisterViewModel extends ViewModel {
    private MutableLiveData<RegisterModel> userRegister;
    private MutableLiveData<SimplePojo> verification;
    private MutableLiveData<OtpPojo> resend;
    private MutableLiveData<GetProfilePojo> login;
    private MutableLiveData<GetProfilePojo> get_profile;
    private MutableLiveData<GetProfilePojo> update_profile;

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
    public LiveData<OtpPojo> resend(final Activity activity,String id){
        resend=new MutableLiveData<>();
        Api api=ApiClient.getApiClient().create(Api.class);
        api.resendToken(id).enqueue(new Callback<OtpPojo>() {
            @Override
            public void onResponse(Call<OtpPojo> call, Response<OtpPojo> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                 resend.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OtpPojo> call, Throwable t) {
                Toast.makeText(activity, "failed", Toast.LENGTH_SHORT).show();
            }
        });
        return resend;
    }

    public LiveData<GetProfilePojo> login(final Activity activity,String email, String password, String device_type, final String reg_id){
        login=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.UserLogin(email,password,device_type,reg_id).enqueue(new Callback<GetProfilePojo>() {
            @Override
            public void onResponse(Call<GetProfilePojo> call, Response<GetProfilePojo> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    login.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<GetProfilePojo> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return login;
    }

    public LiveData<GetProfilePojo> getProfile(final Activity activity,String id){
        get_profile=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.getProfile(id).enqueue(new Callback<GetProfilePojo>() {
            @Override
            public void onResponse(Call<GetProfilePojo> call, Response<GetProfilePojo> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    get_profile.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetProfilePojo> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();

            }
        });
        return get_profile;
    }

    public LiveData<GetProfilePojo> updateProfile(final Activity activity,RequestBody userId, RequestBody name, RequestBody phone, MultipartBody.Part image){
        update_profile=new MutableLiveData<>();

        Api api=ApiClient.getApiClient().create(Api.class);
        api.updateProfile(userId,name,phone,image).enqueue(new Callback<GetProfilePojo>() {
            @Override
            public void onResponse(Call<GetProfilePojo> call, Response<GetProfilePojo> response) {
                if (response.body().getSuccess().equalsIgnoreCase("1")){
                    update_profile.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<GetProfilePojo> call, Throwable t) {
                Toast.makeText(activity, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        return update_profile;
    }


}
