package com.example.freshup;

import com.example.freshup.Models.GetHomeDataModel;
import com.example.freshup.Models.GetProfilePojo;
import com.example.freshup.Models.GetServicesDataModel;
import com.example.freshup.Models.OtpPojo;
import com.example.freshup.Models.RegisterModel;
import com.example.freshup.Models.SimplePojo;
import com.example.freshup.Models.SingleProductCategoryModel;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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

    @FormUrlEncoded
    @POST("userLogin")
    Call<GetProfilePojo> UserLogin(@Field("email") String email,
                                   @Field("password") String password,
                                   @Field("reg_id") String reg_id,
                                   @Field("device_type") String device_type);

    @FormUrlEncoded
    @POST("resendVerificationToken")
    Call<OtpPojo> resendToken(@Field("id") String id);

    @FormUrlEncoded
    @POST("userGetProfile")
    Call<GetProfilePojo> getProfile(@Field("userId") String id);

    @Multipart
    @POST("userUpdateProfile")
    Call<GetProfilePojo> updateProfile(@Part("userId") RequestBody userId,
                                       @Part("name") RequestBody name,
                                       @Part("phone") RequestBody phone,
                                       @Part MultipartBody.Part image);

    @FormUrlEncoded
    @POST("forgotPassword")
    Call<Map> forgetPassword(@Field("email") String email);


    @FormUrlEncoded
    @POST("changePassword")
    Call<Map> changePassword(@Field("userId") String userid,
                             @Field("old_password") String old_password,
                             @Field("new_password") String new_password);
    @GET("getServices")
    Call<GetHomeDataModel> getServices();

    @GET("getProduct")
    Call<GetHomeDataModel> getProduct();

    @FormUrlEncoded
    @POST("getSubServices")
    Call<GetServicesDataModel> getSubServices(@Field("serviceId") String serviceId);


    @FormUrlEncoded
    @POST("getSubCategoryAndProduct")
    Call<SingleProductCategoryModel> getProducts(@Field("categoryId") String categoryId,
                                                 @Field("userId") String userId);

}

