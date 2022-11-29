package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import android.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat Sw24h;
    SwitchCompat SwSombre;
    SwitchCompat Sw3D;
    SwitchCompat SwSalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Sw24h = findViewById(R.id.format24);
        SwSombre = findViewById(R.id.mode_sombre);
        Sw3D = findViewById(R.id.mode_3d);
        SwSalle = findViewById(R.id.affichage_salle);

        SharedPreferences sp = getSharedPreferences("Save",MODE_PRIVATE); // Groupe Save

        Sw24h.setChecked(sp.getBoolean("12h24",false));
        SwSombre.setChecked(sp.getBoolean("Dark",false));
        Sw3D.setChecked(sp.getBoolean("2D3D",false));
        SwSalle.setChecked(sp.getBoolean("Salle",false));

        Sw24h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sw24h.isChecked())
                {
                    // Bouton Switch ON
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("12h24",true); // On mets true à l'étiquette value du groupe save
                    editor.apply();
                    Sw24h.setChecked(true);
                }
                else
                {
                    // Bouton Switch OFF
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("12h24",false); // On mets false à l'étiquette value du groupe save
                    editor.apply();
                    Sw24h.setChecked(false);
                }
            }
        });
        SwSombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwSombre.isChecked())
                {
                    // Bouton Switch ON
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Dark",true); // On mets true à l'étiquette value du groupe save
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); // Utilise en permanence les ressources night
                    SwSombre.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Dark",false); // On mets false à l'étiquette value du groupe save
                    editor.apply();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); // Utilise en permanence les ressources normals
                    SwSombre.setChecked(false);
                }
            }
        });
        Sw3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Sw3D.isChecked())
                {
                    // Bouton Switch ON
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("2D3D",true); // On mets true à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("2D3D",false); // On mets false à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(false);
                }
            }
        });
        SwSalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SwSalle.isChecked())
                {
                    // Bouton Switch ON
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Salle",true); // On mets true à l'étiquette value du groupe save
                    editor.apply();
                    SwSalle.setChecked(true);

                }
                else
                {
                    // Bouton Switch OFF
                    SharedPreferences.Editor editor = getSharedPreferences("Save",MODE_PRIVATE).edit();
                    editor.putBoolean("Salle",false); // On mets false à l'étiquette value du groupe save
                    editor.apply();
                    Sw3D.setChecked(false);
                }
            }
        });
    }

    public void retour(View view){
        Intent bouton_retour = new Intent(this, TableActivity.class);
        startActivity(bouton_retour);
    }
}