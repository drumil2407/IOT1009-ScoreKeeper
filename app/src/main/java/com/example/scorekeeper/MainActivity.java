package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView item;
    Button increase, decrease;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find a text view when button click
        item = (TextView) findViewById(R.id.team1_score);
        increase = (Button) findViewById(R.id.team2_button2);
        decrease = (Button) findViewById(R.id.team2_button3);


        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                item.setText(String.valueOf(i));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //i will stop at 1 then it will not go negative
                if (i != 0)
                    i--;
                item.setText(String.valueOf(i));
            }
        });


    }

    public void radioButtonChecked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButton:
                if (checked)
                    break;
            case R.id.radioButton2:
                if (checked)
                    break;
        }

    }
}