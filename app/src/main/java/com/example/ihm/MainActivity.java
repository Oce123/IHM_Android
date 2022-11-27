package com.example.ihm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
//AppCompatActivity

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.*;
import java.io.*;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    //MainFragment mainFragment;

    EditText numero, heure, heure2;
    Button b;

    Helper h = new Helper(MainActivity.this);

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
        //HttpConnection();
    }

    public void redirection(View view) {
        Intent redirect = new Intent(this, TableActivity.class);
        startActivity(redirect);
    }

    public void liste_salle(View view){
        Intent bouton_salle = new Intent(this, ListeSalle.class);
        startActivity(bouton_salle);
    }

    /*private InputStream HttpConnection(){
        InputStream in = null;
        int resCode = -1;
        try {
            String link = "https://planif.esiee.fr:443/jsp/webapi?function=connect&login=lecteur1&password=";
            URL url = new URL(link);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            resCode = httpConn.getResponseCode();
            if (resCode == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
            // get data
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(url.openStream());

        }
        catch (MalformedURLException e) {
            e.printStackTrace();}
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (SAXException e) {
            e.printStackTrace();
        }
        catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return in;
    }
     */

}





