package practice.module.com.hackathon.services;

import com.google.gson.JsonElement;

import java.util.List;

import practice.module.com.hackathon.networking.getData;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ProfileData {

    @GET("profile/")
    Call<List<getData>> data(@Query("uniq_id") int object);


}
