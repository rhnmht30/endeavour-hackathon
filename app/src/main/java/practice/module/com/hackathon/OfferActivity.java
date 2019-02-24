package practice.module.com.hackathon;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;

public class OfferActivity extends AppCompatActivity {

Button btn;
TextView tv;
EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);
final TimePicker picker = findViewById(R.id.timePicker);
picker.setIs24HourView(true);

editText.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        TimePicker picker1 = findViewById(R.id.timePicker);
    }
});

    }
}
