package practice.module.com.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import practice.module.com.hackathon.models.AddCarRequest;
import practice.module.com.hackathon.networking.Api;
import practice.module.com.hackathon.services.AddCar;
import practice.module.com.hackathon.services.SignIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCarActivity extends AppCompatActivity {

    private Button addCarBtn,skipBtn;
    private AddCarRequest addCarRequest;
    private EditText vehicleName,vehicleNumber,emptySeats;
    private SharedPreferences sharedPreferences;
    private JsonElement newAddCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        addCarBtn = findViewById(R.id.addCarButton);
        skipBtn = findViewById(R.id.skipCarButton);

        vehicleName = findViewById(R.id.editText_addcar_vehicle_name);
        vehicleNumber = findViewById(R.id.editText_addcar_vehicle_number);
        emptySeats = findViewById(R.id.editText_addcar_vehicle_empty_seats);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(AddCarActivity.this,MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });


        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCar();
                Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void addCar() {
        addCarRequest = new AddCarRequest();
        addCarRequest.setCarname(vehicleName.getText().toString());
        addCarRequest.setCarNumber(vehicleNumber.getText().toString());
        addCarRequest.setCarseats(Integer.valueOf(emptySeats.getText().toString()));

        sharedPreferences = getSharedPreferences("get",MODE_PRIVATE);
        int uniq_id  = sharedPreferences.getInt("uniq_id",0);

        addCarRequest.setAddedBy(uniq_id);
        Log.e("uniq_id",String.valueOf(uniq_id));
        Log.e("addCar", new Gson().toJson(addCarRequest));

        AddCar addCar = Api.createService(AddCar.class);
        Call<JsonElement> call = addCar.send(addCarRequest);
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                if (response.code() == 200) {
                    newAddCar = response.body();
                    Log.e("Res", new Gson().toJson(newAddCar));

                    Intent intent = new Intent(AddCarActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    vehicleName.setText("");
                    vehicleNumber.setText("");
                    emptySeats.setText("");
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

            }
        });

    }


    }

