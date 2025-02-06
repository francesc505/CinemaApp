package com.example.afinal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;


public class ThirdActivity extends AppCompatActivity{

    EditText EditTextsource, EditTextdestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ArrayList<String> cinemasList = getIntent().getStringArrayListExtra("cinema");
        if (cinemasList != null) {
            for (String cinema : cinemasList) {
                Log.d("risultato", "cinema: " + cinema);
            }
        } else {
            Log.d("sbagliato", "cinema list is null");
        }


        EditTextdestination = findViewById(R.id.destination);
        Button btn = findViewById(R.id.btnSubmit);
        // gestione della posizione corrente !
        EditTextsource = findViewById(R.id.source);

        EditTextdestination.setText(cinemasList.get(0));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //current position

                String source = EditTextsource.getText().toString();
                String destination  = EditTextdestination.getText().toString();
                if (source.equals("") && destination.equals("")){
                    Toast.makeText(getApplicationContext(), "enter both source and destination", Toast.LENGTH_LONG).show();
                }
                else{
                    Uri uri = Uri.parse("https://www.google.com/maps/dir/" + source + "/" + destination);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }



}//FINE