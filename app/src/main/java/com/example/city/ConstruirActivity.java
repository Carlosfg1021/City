package com.example.city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.city.Adaptadores.AdaptadorCiudad;
import com.example.city.datos.Cityrow;

import java.util.ArrayList;

public class ConstruirActivity extends AppCompatActivity {


    private ListView lvItems;
    private AdaptadorCiudad adaptador;
    private TextView txtEdificioSeleccionado;
    private TextView txtPrecioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construir);

    lvItems = findViewById(R.id.listaEdificios);
    txtEdificioSeleccionado = findViewById(R.id.edificioSeleccionado);
    txtPrecioSeleccionado = findViewById(R.id.precioSeleccionado);

    adaptador = new AdaptadorCiudad(this, getArrayItems());

    lvItems.setAdapter(adaptador);


    lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    txtEdificioSeleccionado.setText("Casa 1");
                    txtPrecioSeleccionado.setText("250");
                    break;

                case 1:

                    break;
            }


        }
    });

    }



    private ArrayList<Cityrow> getArrayItems(){

        ArrayList<Cityrow> listItems = new ArrayList<>();

        listItems.add(new Cityrow("1","Casa 1","250", R.drawable.a1_casa));



        return listItems;
    }




}