package practice.module.com.hackathon;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.support.v7.widget.Toolbar;

import com.evolve.backdroplibrary.BackdropContainer;




public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private BackdropContainer backdropContainer;
    SharedPreferences sharedPreferences;
    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
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
