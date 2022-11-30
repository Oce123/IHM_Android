package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class TableActivity extends AppCompatActivity {

    private Spinner spinnerHeure;
    private Spinner spinnerHeure_de_fin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        this.spinnerHeure = (Spinner) findViewById(R.id.heure_de_debut);
        //Debut format 24h
        Heure[] heure = HeureDataUtils.getHeure();

        ArrayAdapter<Heure> adapter = new ArrayAdapter<Heure>(this,
                android.R.layout.simple_spinner_item,
                heure);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerHeure.setAdapter(adapter);

        this.spinnerHeure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.spinnerHeure_de_fin = (Spinner) findViewById(R.id.heure_de_fin);
        Heure_de_fin[] heure_de_fins = HeureDataUtils.getHeure_de_fin();

        ArrayAdapter<Heure_de_fin> adapter2 = new ArrayAdapter<Heure_de_fin>(this,
                android.R.layout.simple_spinner_item,
                heure_de_fins);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinnerHeure_de_fin.setAdapter(adapter2);

        this.spinnerHeure_de_fin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onItemSelectedHandler2(parent, view, position, id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Fin format 24h

    }

    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Heure heure = (Heure) adapter.getItem(position);

        Toast.makeText(getApplicationContext(), "Heure de début selectionnée: " + heure.getFullHeure() ,Toast.LENGTH_SHORT).show();
    }

    private void onItemSelectedHandler2(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Heure_de_fin heure_de_fin = (Heure_de_fin) adapter.getItem(position);

        Toast.makeText(getApplicationContext(), "Heure de fin selectionnée: " + heure_de_fin.getFullHeure_de_fin() ,Toast.LENGTH_SHORT).show();
    }


    public void parametres(View view){
        Intent bouton_parametres = new Intent(this, SettingsActivity.class);
        startActivity(bouton_parametres);
    }

    public void deconnexion(View view){
        Intent bouton_deconnexion = new Intent(this, WelcomeActivity.class);
        startActivity(bouton_deconnexion);
    }

    public void back(View view){
        Intent bouton_back = new Intent(this, MainActivity.class);
        startActivity(bouton_back);
    }
}