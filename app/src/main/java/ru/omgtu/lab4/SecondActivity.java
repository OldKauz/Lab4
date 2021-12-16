package ru.omgtu.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    TextView text_view;
    Button result_btn, play_store;

    static final int GALLERY_REQUEST = 1;

    int day, month, year, hour, minute;
    String day_x, month_x, year_x, hour_x, minute_x;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text_view = (TextView) findViewById(R.id.text_view);
        result_btn = (Button) findViewById(R.id.result_btn);
        play_store = (Button) findViewById(R.id.play_store);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null){
            if(arguments.get("editText").toString().equals(""))
            {
                text_view.setTextColor(Color.RED);
                text_view.setText("Вы не ввели имя!");
            }
            else
            {
                text_view.setTextColor(Color.WHITE);
                text_view.setText("Ваше имя " + arguments.get("editText").toString());
            }
        }

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar c = Calendar.getInstance();
                year = c.get(Calendar.YEAR);
                month = c.get(Calendar.MONTH);
                day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(SecondActivity.this, SecondActivity.this, year, month, day);
                datePickerDialog.show();
            }
        });

        play_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));

                 try {
                     startActivity(new Intent(intent.setPackage("com.android.vending")));
                 }
                 catch (android.content.ActivityNotFoundException exception) {startActivity(intent);}
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

        year_x = Integer.toString(i);
        month_x = Integer.toString(i1+1);
        day_x = Integer.toString(i2);

        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(SecondActivity.this, SecondActivity.this, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hour_x = Integer.toString(i);
        minute_x = Integer.toString(i1);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("hour", hour_x);
        intent.putExtra("minute", minute_x);
        intent.putExtra("year", year_x);
        intent.putExtra("month", month_x);
        intent.putExtra("day", day_x);
        startActivity(intent);
    }
}