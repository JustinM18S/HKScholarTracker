package com.example.hkscholarhub;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("api/auth/login")
    Call<LoginResponse> login(
            @Field("email") String email,
            @Field("password") String password
    );
}