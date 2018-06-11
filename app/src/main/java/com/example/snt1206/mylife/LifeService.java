package com.example.snt1206.mylife;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by tao.j on 2016/7/26.
 */
public interface LifeService {

    String HEADER_AUTHORIZATION_KEY = "zGuC1ylBfVvXb-acprZS0xba7IdVtdr7V2NqWO0IhF-GqIvQxuM";

    @POST("user/login")
    Call<LoginResponse> login(@Body LoginRequestBody requestBody);

}