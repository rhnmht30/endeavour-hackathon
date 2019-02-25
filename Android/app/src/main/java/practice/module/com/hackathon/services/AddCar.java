package practice.module.com.hackathon.services;

import com.google.gson.JsonElement;

import practice.module.com.hackathon.models.AddCarRequest;
import practice.module.com.hackathon.models.SignupRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddCar {

    @POST("addcar/")
    Call<JsonElement> send(@Body AddCarRequest object);

}
