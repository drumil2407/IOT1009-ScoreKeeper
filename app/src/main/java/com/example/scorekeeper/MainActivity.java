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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    RadioGroup scores;
    RadioButton rb1, rb2;

    TextView item;
    TextView item1;
    Button increase, decrease;
    Button increase1, decrease1;
    int i = 0;
    int j = 0;
    SwitchCompat switch3;
    Switch switch1;
    Switch switch2;
    SharedPreferences sharedPreferences = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scores = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);

        //find a text view when button click
        item = (TextView) findViewById(R.id.team1_score);
        item1 = (TextView) findViewById(R.id.team2_score);

        increase = (Button) findViewById(R.id.team2_button2);
        decrease = (Button) findViewById(R.id.team1_button3);

        increase1 = (Button) findViewById(R.id.team1_button2);
        decrease1 = (Button) findViewById(R.id.team2_button3);

        switch3 = findViewById(R.id.switch3);
        switch1 = findViewById(R.id.switch1);
        switch2 = findViewById(R.id.switch2);

        //set enabled used for enabling and disabling buttons
        increase.setEnabled(false);
        decrease.setEnabled(false);

        increase1.setEnabled(false);
        decrease1.setEnabled(false);



        //create a shared preference object for boolean for dark theme
        sharedPreferences = getSharedPreferences("dark", 0);

        //save dark mode in this object
        boolean bool = sharedPreferences.getBoolean("dark_mode",true);

        if(bool)
        {
            //app compat delegate method will get the dark mode object
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            switch3.setChecked(true);
        }


        //switch listener method when user toggle the switch
        //if user toggle the button then it is true and dark mode is on otherwise it is normal
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
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

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if(b)
                {
                    increase.setEnabled(true);
                    increase.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            i++;
                            item.setText(String.valueOf(i));
                        }
                    });
                    decrease.setEnabled(true);
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
                else
                {
                    increase.setEnabled(false);
                    decrease.setEnabled(false);
                }

            }
        });
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean c)
            {
                if(c)
                {
                    increase1.setEnabled(true);
                    increase1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view)
                        {

                            j++;
                            item1.setText(String.valueOf(j));
                        }
                    });
                    decrease1.setEnabled(true);
                    decrease1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //i will stop at 1 then it will not go negative
                            if (j != 0)
                                j--;
                            item1.setText(String.valueOf(j));
                        }
                    });
                }
                else
                {
                    increase1.setEnabled(false);
                    decrease1.setEnabled(false);
                }

            }
        });

        scores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i)
            {
                RadioButton radioButton = (RadioButton) findViewById(i);
                Toast.makeText(getApplicationContext(),radioButton.getText(),Toast.LENGTH_LONG).show();
                if(i == -1)
                {
                    Toast.makeText(MainActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,radioButton.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}


