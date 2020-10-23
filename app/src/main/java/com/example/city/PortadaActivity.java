package com.example.city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.city.Inicio.InicioSesion;
import com.example.city.Inicio.RegistroActivity;
import com.example.city.Modelos.MainActivity;

public class PortadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portada);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(PortadaActivity.this, InicioSesion.class);
                startActivity(intent);
            }
        }, 2000);
    }
}