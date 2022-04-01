package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView item;
    Button increase, decrease;
    int i = 0;
    SwitchCompat switch3;
    SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find a text view when button click
        item = (TextView) findViewById(R.id.team1_score);
        increase = (Button) findViewById(R.id.team2_button2);
        decrease = (Button) findViewById(R.id.team2_button3);
        switch3 = findViewById(R.id.switch3);

        //create a shared preference object for boolean for dark theme
        sharedPreferences = getSharedPreferences("dark", 0);

        //save dark mode in this object
        Boolean bool = sharedPreferences.getBoolean("dark_mode",true);

        if(bool)
        {
            //appcompatedelegate method will get the dark mode object
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switch3.setChecked(true);
        }


        //switch listener method when user toggle the switch
        //if user toggle the button then it is true and dark mode is on otherwise it is normal
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switch3.setChecked(true);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("dark_mode",true);
                    editor.commit();
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switch3.setChecked(false);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("dark_mode",false);
                    editor.commit();
                }
            }
        });


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