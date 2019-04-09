package com.example.freshup;

import com.example.freshup.Models.RegisterModel;
import com.example.freshup.Models.SimplePojo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("userRegister")
    Call<RegisterModel> UserRegister(@Field("name") String name,
                                     @Field("email") String email,
                                     @Field("phone") String phone,
                                     @Field("password") String password,
                                     @Field("device_type") String device_type,
                                     @Field("reg_id") String reg_id);

    @FormUrlEncoded
    @POST("matchVerificationToken")
    Call<SimplePojo> matchToken(@Field("id") String id,
                                @Field("token") String token);


}
