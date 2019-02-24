package practice.module.com.hackathon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import practice.module.com.hackathon.models.LoginRequest;
import practice.module.com.hackathon.models.LoginResponse;
import practice.module.com.hackathon.networking.Api;
import practice.module.com.hackathon.services.Login;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Context context;
    private FloatingActionButton fab_login;
    private TextView signUpAppeal;
    private LoginRequest loginRequest;
    private EditText username, password;
    private LoginResponse loginResponse;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        context = this.getApplicationContext();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.login_page);

        fab_login = findViewById(R.id.fab_login);
        username = findViewById(R.id.editText_login_email);
        password = findViewById(R.id.editText_login_password);

        signUpAppeal = findViewById(R.id.text_signUpAppeal);

        fab_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLogin();
            }
        });

        signUpAppeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signUpIntent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(signUpIntent);
                finish();
            }
        });
    }

    public void setLogin() {
        loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        Log.e("Check", new Gson().toJson(loginRequest));

        editor = getSharedPreferences("get", MODE_PRIVATE).edit();
        Login login = Api.createService(Login.class);
        Call<LoginResponse> call = login.login(loginRequest);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.e("Reeee", "hello");
                if (response.code() == 200) {
                    loginResponse = response.body();
                    editor.putInt("uniq_id", response.body().getUniqId());
                    editor.apply();
                    Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                    startActivity(intent);


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                editor.apply();
                startActivity(intent);
            }
        });

    }
}