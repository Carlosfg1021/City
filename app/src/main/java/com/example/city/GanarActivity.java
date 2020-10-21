package com.example.city;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.Inicio.AuthActivity;
import com.example.city.Modelos.MainActivity;

import org.w3c.dom.Text;

public class GanarActivity extends AppCompatActivity {

    String opcion;
    TextView titulo;
    TextView cantidadxp;
    ImageView fondo;
    Button btncontinuar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganar);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString("opcion");
        titulo = (TextView) findViewById(R.id.titulo);
        cantidadxp = (TextView) findViewById(R.id.cantidadxp);
        fondo = (ImageView) findViewById(R.id.fondo);
        btncontinuar = (Button) findViewById(R.id.btnContinuar);





        if(opcion.equals("ganar")){
            //Toast.makeText(this, "MAS PUNTOS", Toast.LENGTH_SHORT).show();
            titulo.setText("¡Ganaste!");
            cantidadxp.setText("+25XP");



        }

        if(opcion.equals("perder")){
           // Toast.makeText(this, "MENOS PUNTOS", Toast.LENGTH_SHORT).show();
            titulo.setText("¡Perdiste!");
            cantidadxp.setText("-25XP");
            fondo.setBackgroundColor(Color.rgb(252, 91, 91));
        }

        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });





    }
}