package practice.module.com.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import practice.module.com.hackathon.models.LoginResponse;
import practice.module.com.hackathon.networking.Api;
import practice.module.com.hackathon.networking.getData;
import practice.module.com.hackathon.services.Login;
import practice.module.com.hackathon.services.ProfileData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    private TextView profileNameTV,profileUsernameTV,profilePhoneTV,profileGenderTV,profileEmailTV,vehicleNameTV,vehicleNumberTV,vehicleSeatsTV;
    private List<getData> getData= new ArrayList<>();
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileNameTV = findViewById(R.id.profile_name);
        profileUsernameTV = findViewById(R.id.profile_user_name);
        profilePhoneTV = findViewById(R.id.profile_phone);
        profileGenderTV = findViewById(R.id.profile_gender);
        profileEmailTV = findViewById(R.id.profile_email);

        vehicleNameTV = findViewById(R.id.vehicle_name);
        vehicleNumberTV = findViewById(R.id.vehicle_number);
        vehicleSeatsTV = findViewById(R.id.vehicle_seat);
        setProfileData();
    }

    private void setProfileData() {

        ProfileData profileData = Api.createService(ProfileData.class);

        sharedPreferences = getSharedPreferences("get",MODE_PRIVATE);
        int uniq_id  = sharedPreferences.getInt("uniq_id",0);

        retrofit2.Call<List<getData>> call = profileData.data(uniq_id);
        call.enqueue(new Callback<List<getData>>() {
            @Override
            public void onResponse(retrofit2.Call<List<getData>> call, Response<List<getData>> response) {


                    getData = response.body();
                Log.e("check", new Gson().toJson(getData));
                    profileNameTV.setText(getData.get(0).getUserdata().getName());
                    profileUsernameTV.setText(getData.get(0).getUserdata().getUsername());
                    profilePhoneTV.setText(getData.get(0).getUserdata().getMobile());
                    profileGenderTV.setText(getData.get(0).getUserdata().getGender());
                    profileEmailTV.setText(getData.get(0).getUserdata().getEmail());

//                    vehicleNameTV.setText(getData.get(0).getCar().getCarname());
//                    vehicleNumberTV.setText(getData.get(0).getCar().getCarNumber());
//                    vehicleSeatsTV.setText(getData.get(0).getCar().getCarseats());

            }

            @Override
            public void onFailure(Call<List<getData>> call, Throwable t) {
                t.printStackTrace();

            }
        });



    }

}
