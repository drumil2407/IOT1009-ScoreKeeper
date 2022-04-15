package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class settings extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tx1,tx2;

        tx1 = findViewById(R.id.textView3);
        tx2 = findViewById(R.id.textView5);

        //get scores from last shared preference and store in sp1
        SharedPreferences sp1 = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);

        String a = sp1.getString("Team1Scores","");
        String b = sp1.getString("Team2Scores","");

        tx1.setText(a);
        tx2.setText(b);


    }

}