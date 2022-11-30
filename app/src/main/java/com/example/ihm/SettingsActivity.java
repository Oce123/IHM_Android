package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;
import android.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void retour(View view){
        Intent bouton_retour = new Intent(this, TableActivity.class);
        startActivity(bouton_retour);
    }


}