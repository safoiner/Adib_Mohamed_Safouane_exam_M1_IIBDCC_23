package com.example.adib_mohamed_safouane_exam_m1_iibdcc_23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button ShowMap;







    private EditText ipEditText;
    private TextView cityTextView;
    private TextView regionTextView;
    private TextView countryTextView;
    private Button getInfoButton;
    private TextView locationTextView;
    public static String[] latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipEditText = findViewById(R.id.ipEditText);
        cityTextView = findViewById(R.id.cityTextView);
        regionTextView = findViewById(R.id.regionTextView);
        countryTextView = findViewById(R.id.countryTextView);
        getInfoButton = findViewById(R.id.getInfoButton);
        locationTextView = findViewById(R.id.locationTextView);


        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ipAddress = ipEditText.getText().toString();
                new GetIpInfoTask(new GetIpInfoTask.OnIpInfoResultListener() {
                    @Override
                    public void onResult(String[] ipInfo) {
                        if (ipInfo != null) {
                            cityTextView.setText("City: " + ipInfo[0]);
                            regionTextView.setText("Region: " + ipInfo[1]);
                            countryTextView.setText("Country: " + ipInfo[2]);
                            latLng = ipInfo[3].split(",");
                            locationTextView.setText("lat "+latLng[0]+","+"lng"+latLng[1]);

                        }
                    }
                }).execute(ipAddress);
            }
        });

        // affichage de la map le mapd est deja intiliser par un point au cas pu les doner ne seront pas recus


        ShowMap = findViewById(R.id.button);
        ShowMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });
    }









}

