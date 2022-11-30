package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.webkit.URLUtil;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
//AppCompatActivity


import java.net.*;
import java.io.*;

import android.webkit.URLUtil;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {
    //MainFragment mainFragment;

    EditText numero, heure, heure2;
    Button b;

    Helper h = new Helper(MainActivity.this);

    public static final int PERM_COMPLETED_STORAGE_ACCESS = 1;
    public static final int WRITE_FILE_REQUEST_CODE = 43;
    MainFragment mainFragment;
    public static String id = "test_channel_01";


    private Spinner spinnerheure;

    TextView logger;
    String TAG = "MainFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        numero = findViewById(R.id.numero);
        heure = findViewById(R.id.heure);
        heure2 = findViewById(R.id.heure_fin);
        b = findViewById(R.id.ajouter);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salle_disponible s = new Salle_disponible(numero.getText().toString(),Double.parseDouble(heure.getText().toString()), Double.parseDouble(heure2.getText().toString()));
                h.insertSalle(s);
                Intent i = new Intent(MainActivity.this, ListeSalle.class);
                startActivity(i);
            }
        });

        //connexion a http
        logger = findViewById(R.id.logger);
        findViewById(R.id.btn_file).setOnClickListener((view) -> {CheckPerm();} );

        };

        // datasource = new CommentsDataSource(this);
        //datasource.open();

        //List<Comment> values = datasource.getAllComments();

        // utilisez SimpleCursorAdapter pour afficher les
        // éléments dans une ListView
        // ArrayAdapter<Comment> adapter = new ArrayAdapter<Comment>(this,
        //       android.R.layout.simple_list_item_1, values);
        //setListAdapter(adapter);


    public void redirection(View view) {
        Intent redirect = new Intent(this, TableActivity.class);
        startActivity(redirect);
    }

    public void liste_salle(View view){
        Intent bouton_salle = new Intent(this, ListeSalle.class);
        startActivity(bouton_salle);
    }



    private void createFile(String mimeType, String fileName) {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        // Create a file with the requested MIME type.
        intent.setType(mimeType);
        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, MainActivity.WRITE_FILE_REQUEST_CODE);
    }


    public void downloadFile() {
        URL url = null;
        String file = "";

        try {

            url = new URL("XXX");

            //yes, I could write the file name, but this means I don't have think it about if I change files.
            file = URLUtil.guessFileName(url.getFile(), null, null);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {  //storage sucks now..
                createFile("image/png",file);
                return;  //to the activityreturned ...;
            }

        } catch (MalformedURLException e) {

            e.printStackTrace();
        }

        //setup intent and send it.
        Intent getfile = new Intent(this.getBaseContext(), FileDownloaderIntentService.class);
        getfile.putExtra("FILE", file);
        getfile.putExtra("URL", url);
        this.startService(getfile);
    }




    public void CheckPerm() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            //I'm on not explaining why, just asking for permission.
            Log.v(TAG, "asking for permissions");
            //note, not ActivityCompat. which sends it to the activity, instead of the fragment.  the super the activity didn't help.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        MainActivity.PERM_COMPLETED_STORAGE_ACCESS);
            }

        } else {
            logger.append("Contact Write Access: Granted\n");
            downloadFile();
        }

    }


}





