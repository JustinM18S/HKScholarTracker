package com.example.hkscholarhub;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface api {
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginResponse> loginUser(
            @Field("email") String email,
            @Field("password") String password
    );
}
