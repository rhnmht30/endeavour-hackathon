package practice.module.com.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.evolve.backdroplibrary.BackdropContainer;




public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BackdropContainer backdropContainer;
    SharedPreferences sharedPreferences;
    private TextView homeTV,profileTV,addCarTV,rewardsTV,signoutTV;
    private Button findBtn,offerBtn;
    @Override
       protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        homeTV = findViewById(R.id.nav_home);
        profileTV = findViewById(R.id.nav_profile);
        addCarTV = findViewById(R.id.nav_add_car);
        rewardsTV = findViewById(R.id.nav_rewards);
        signoutTV = findViewById(R.id.nav_sign_out);

        findBtn = findViewById(R.id.findCarBtn);
        offerBtn = findViewById(R.id.offerCarBtn);


        offerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,OfferActivity.class);
                startActivity(intent);
            }
        });

        homeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backdropContainer.closeBackview();

            }
        });
        profileTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backdropContainer.closeBackview();
                Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        addCarTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backdropContainer.closeBackview();
                Intent newIntent = new Intent(MainActivity.this,AddCarActivity.class);
                startActivity(newIntent);
                finish();
            }
        });

        rewardsTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backdropContainer.closeBackview();
                Toast.makeText(MainActivity.this, "Rewards", Toast.LENGTH_SHORT).show();
            }
        });

        signoutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               System.exit(0);
            }
        });


        sharedPreferences = getSharedPreferences("get",MODE_PRIVATE);
        int uniq_id  = sharedPreferences.getInt("uniq_id",0);
        Log.e("uniq_id",String.valueOf(uniq_id));
        backdropContainer =(BackdropContainer)findViewById(R.id.backdropcontainer);

        int height = this.getResources().getDimensionPixelSize(R.dimen.sneek_height);
        backdropContainer.attachToolbar(toolbar)
                .dropInterpolator(new LinearInterpolator())
                .dropHeight(height)
                .build();

    }
}
