// contient la splash page et renvoie via intend à la page d'accueil

package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SchemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schemes);
    }
}