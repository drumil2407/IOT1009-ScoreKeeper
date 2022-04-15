package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
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

    TextView items;
    TextView items1;
    Button increase, decrease;
    Button increase1, decrease1;
    Button saveScores;
    int i = 0;
    int j = 0;
    SwitchCompat switch3;
    Switch switch1;
    Switch switch2;
    String tex1,tex2;
    SharedPreferences sharedPreferences = null;
    SharedPreferences s;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scores = (RadioGroup) findViewById(R.id.radioGroup);
        rb1 = (RadioButton) findViewById(R.id.radioButton);
        rb2 = (RadioButton) findViewById(R.id.radioButton2);

        //find a text view when button click
        items = (TextView) findViewById(R.id.team1_score);
        items1 = (TextView) findViewById(R.id.team2_score);

        increase = (Button) findViewById(R.id.team2_button2);
        decrease = (Button) findViewById(R.id.team1_button3);

        increase1 = (Button) findViewById(R.id.team1_button2);
        decrease1 = (Button) findViewById(R.id.team2_button3);
        saveScores = (Button) findViewById(R.id.buttonS);

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
        s = getSharedPreferences("pref", Context.MODE_PRIVATE);



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
                            items.setText(String.valueOf(i));
                        }
                    });
                    decrease.setEnabled(true);
                    decrease.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //i will stop at 1 then it will not go negative
                            if (i != 0)
                                i--;
                            items.setText(String.valueOf(i));
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
                            items1.setText(String.valueOf(j));
                        }
                    });
                    decrease1.setEnabled(true);
                    decrease1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //i will stop at 1 then it will not go negative
                            if (j != 0)
                                j--;
                            items1.setText(String.valueOf(j));
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

        //save scores using shared preference
        saveScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //get text from the scores text views
                tex1 = items.getText().toString();
                tex2 = items1.getText().toString();


                SharedPreferences.Editor editor = s.edit();

                editor.putString("Team1Scores", tex1);
                editor.putString("Team2Scores",tex2);
                editor.commit();

                Toast.makeText(MainActivity.this,"Scores are saved", Toast.LENGTH_SHORT).show();


            }
        });



    }

    //action bar menu method
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem menuItem)
    {

        int id = menuItem.getItemId();

        if(id == R.id.Settings)
        {
            Intent it = new Intent(MainActivity.this,settings.class);
            startActivity(it);
            return true;
        }
        return  super.onOptionsItemSelected(menuItem);
    }

    //toast message for name and developer information

    public void aboutClick(MenuItem item)
    {
        Toast.makeText(MainActivity.this,"DrumilShekhda-IOT1009-ScoreKeeper", Toast.LENGTH_SHORT).show();
    }





}


