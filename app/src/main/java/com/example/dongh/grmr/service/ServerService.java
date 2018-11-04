package com.example.dongh.grmr.service;

import com.example.dongh.grmr.Constructor.Contributor;
import com.example.dongh.grmr.Constructor.GM_Users;
import com.example.dongh.grmr.Constructor.UserInformation;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by SYL on 2018-03-25.
 */

public interface ServerService {

 // final String Base_URL = "http://52.197.13.138";
//test
    /******************************POST************************************/
    @FormUrlEncoded
    @POST("/galmal/signin/facebook")
    Call<Contributor>Facebook_token(@Field("access_token") String FaceToken);

    @FormUrlEncoded
    @POST("/galmal/signin/kakao")
    Call<Contributor>Kakao_token(@Field("access_token") String KakaoToken);


    @FormUrlEncoded
    @POST("/galmal/signup")
    Call<UserInformation> registerUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("nickname") String nickName
    );


    @Multipart
    //@PUT("galmal/restrict/match")
    @POST("galmal/restrict/match")
    Call<ResponseBody> uploadTravel(
            @Part("title") String title,
            @Part("destination") String destination,
            @Part MultipartBody.Part image,
            @Part("startDate") String startDate,
            @Part("endDate") String endDate
    );


    /****************************GET*****************************************/
    /*
    @GET("/galmal/signin/email")
    Call<List<GM_Users>> userList(
            @Query("email") String email,
            @Query("password") String password
    );
    */
    @GET("/galmal/signin/email")
    Call<GM_Users> userList(
            @Query("email") String email,
            @Query("password") String password
    );


}

