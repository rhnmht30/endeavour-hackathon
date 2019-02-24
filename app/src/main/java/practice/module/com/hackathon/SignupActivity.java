package practice.module.com.hackathon;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.List;
import practice.module.com.hackathon.models.SignupRequest;
import practice.module.com.hackathon.networking.Api;
import practice.module.com.hackathon.services.SignIn;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private Context context;
    private FloatingActionButton fab_signup;
    private EditText signup_name,signup_username,signup_password,signup_email,signup_phoneno;
    private Spinner signup_gender;
    private int genderValue;
    private JsonElement newSIgn;
    private SignupRequest signupRequest;
    private List<String> list = new ArrayList<>();
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getApplicationContext();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.signup_page);

        signup_name = findViewById(R.id.editText_signup_name);
        signup_username = findViewById(R.id.editText_signup_username);
        signup_password = findViewById(R.id.editText_signup_password);
        signup_email = findViewById(R.id.editText_signup_email);
        signup_gender = findViewById(R.id.spinner_gender);
        signup_phoneno = findViewById(R.id.editText_signup_phoneno);

        setGender();

        signup_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                genderValue = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        fab_signup = findViewById(R.id.fab_signup);


        fab_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSignUp();
            }
        });


    }

    private void setGender() {

        list.add(0,"Select");
        list.add(1,"Male");
        list.add(2,"Female");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        signup_gender.setAdapter(adapter);
    }

    private void setSignUp() {
        signupRequest = new SignupRequest();
        signupRequest.setName(signup_name.getText().toString());
        signupRequest.setUsername(signup_username.getText().toString());
        signupRequest.setEmail(signup_email.getText().toString());
        signupRequest.setMobile(Integer.valueOf(signup_phoneno.getText().toString()));
        signupRequest.setPassword(signup_password.getText().toString());
        signupRequest.setGender(list.get(genderValue));

        Log.e("sign", new Gson().toJson(signupRequest));

            SignIn signIn = Api.createService(SignIn.class);
            Call<JsonElement> call = signIn.send(signupRequest);
            call.enqueue(new Callback<JsonElement>() {
                @Override
                public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                    if (response.code() == 200) {
                        newSIgn = response.body();
                        Log.e("Res", new Gson().toJson(newSIgn));
//                        if (newSIgn.getName().equals("Username already taken")) {
//                            Toast.makeText(getApplicationContext(), "Username Already exist", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Successfully Registerd", Toast.LENGTH_SHORT).show();
//                            Intent in = new Intent(getApplicationContext(), LoginActivity.class);
//
//                            startActivity(in);
//                        }

                    }
                }

                @Override
                public void onFailure(Call<JsonElement> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            });

        }

        }
