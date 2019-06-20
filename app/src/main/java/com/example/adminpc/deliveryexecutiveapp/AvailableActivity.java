package com.example.adminpc.deliveryexecutiveapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class AvailableActivity extends AppCompatActivity {
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);
        requestPermission();
        client = LocationServices.getFusedLocationProviderClient(this);

        Bundle extras = getIntent().getExtras();
        final String username = extras.getString("username");

        TextView txt_welcome = findViewById(R.id.txt_welcome);
        txt_welcome.setText("Welcome, " + username + "!");

        Button btnAvailable = (Button) findViewById(R.id.btnAvailable);
        Button btnNotAvailable = (Button) findViewById(R.id.btnNotAvailable);
        Button performance = (Button) findViewById(R.id.btnPerformance);

        btnAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(getApplicationContext(), OrdersActivity.class);

                if (ActivityCompat.checkSelfPermission(AvailableActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                client.getLastLocation().addOnSuccessListener(AvailableActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                    if(location != null){
                       i.putExtra("latitude", String.valueOf(location.getLatitude()));
                       i.putExtra("longitude", String.valueOf(location.getLongitude()));
                        i.putExtra("username", username);
                        Toast.makeText(AvailableActivity.this, location.toString(), Toast.LENGTH_SHORT).show();
                        startActivity(i);
                    }
                    }
                });

            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }
}
