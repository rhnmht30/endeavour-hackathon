package practice.module.com.hackathon.services;

import practice.module.com.hackathon.models.LoginRequest;
import practice.module.com.hackathon.models.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Login {

    @POST("login/")
    Call<LoginResponse> login(@Body LoginRequest object);


}
