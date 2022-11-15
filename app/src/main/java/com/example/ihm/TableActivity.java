package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
    }

    public void parametres(View view){
        Intent bouton_parametres = new Intent(this, SettingsActivity.class);
        startActivity(bouton_parametres);
    }

    public void deconnexion(View view){
        Intent bouton_deconnexion = new Intent(this, WelcomeActivity.class);
        startActivity(bouton_deconnexion);
    }
}