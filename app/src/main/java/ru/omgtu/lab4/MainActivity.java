package ru.omgtu.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button main_btn;
    EditText main_edit;
    EditText time, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_edit = (EditText) findViewById(R.id.main_edit);
        main_btn = (Button) findViewById(R.id.main_btn);
        time = (EditText) findViewById(R.id.editTime);
        date = (EditText) findViewById(R.id.editDate);

        time.setFocusable(false);
        time.setLongClickable(false);
        time.setCursorVisible(false);
        date.setFocusable(false);
        date.setLongClickable(false);
        date.setCursorVisible(false);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(".SecondActivity");
                intent.putExtra("editText", main_edit.getText());
                startActivity(intent);
            }
        };
        main_btn.setOnClickListener(oclBtnOk);

        Bundle arguments = getIntent().getExtras();
        if(arguments!=null) {

            date.setText(arguments.get("day").toString() + "." + arguments.get("month").toString() + "." + arguments.get("year").toString());
            time.setText(arguments.get("hour").toString() + ":" + arguments.get("minute").toString());
        }
    }
}