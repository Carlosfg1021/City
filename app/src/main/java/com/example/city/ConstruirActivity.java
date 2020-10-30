package com.example.city;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    private TextView txtIdSeleccionado;
    private ImageView imgEdificioSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construir);

    lvItems = findViewById(R.id.listaEdificios);
    txtEdificioSeleccionado = findViewById(R.id.edificioSeleccionado);
    txtPrecioSeleccionado = findViewById(R.id.precioSeleccionado);
    txtIdSeleccionado = findViewById(R.id.idEdificioSeleccionado);
    imgEdificioSeleccionado = (ImageView) findViewById(R.id.imgEdificioSeleccionado);



    adaptador = new AdaptadorCiudad(this, getArrayItems());

    lvItems.setAdapter(adaptador);


    lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            switch (position){
                case 0:
                    txtEdificioSeleccionado.setText("Garage");
                    txtPrecioSeleccionado.setText("200");
                    txtIdSeleccionado.setText("n1_c1");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c1);
                    break;

                case 1:
                    txtEdificioSeleccionado.setText("Casa 1");
                    txtPrecioSeleccionado.setText("250");
                    txtIdSeleccionado.setText("n1_c2");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c2);

                    break;

                case 2:
                    txtEdificioSeleccionado.setText("Casa 2");
                    txtPrecioSeleccionado.setText("250");
                    txtIdSeleccionado.setText("n1_c3");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c3);

                    break;

                case 3:
                    txtEdificioSeleccionado.setText("Apartamento 1");
                    txtPrecioSeleccionado.setText("700");
                    txtIdSeleccionado.setText("n2_c1");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n2_c1);

                    break;

                case 4:
                    txtEdificioSeleccionado.setText("Apartamento 2");
                    txtPrecioSeleccionado.setText("1000");
                    txtIdSeleccionado.setText("n2_c2");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n2_c2);

                    break;

                case 5:
                    txtEdificioSeleccionado.setText("Edificio 1");
                    txtPrecioSeleccionado.setText("3000");
                    txtIdSeleccionado.setText("n3_c1");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n3_c1);

                    break;

                case 6:
                    txtEdificioSeleccionado.setText("Edificio 2");
                    txtPrecioSeleccionado.setText("5000");
                    txtIdSeleccionado.setText("n3_c2");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n3_c2);

                    break;
            }


        }
    });

    }



    private ArrayList<Cityrow> getArrayItems(){

        ArrayList<Cityrow> listItems = new ArrayList<>();

        listItems.add(new Cityrow("n1_c1","Garage","Precio: 200", R.drawable.n1_c1));
        listItems.add(new Cityrow("n1_c2","Casa 1","Precio: 250", R.drawable.n1_c2));
        listItems.add(new Cityrow("n1_c3","Casa 2","Precio: 250", R.drawable.n1_c3));
        listItems.add(new Cityrow("n2_c1","Apartamento 1","Precio: 700", R.drawable.n2_c1));
        listItems.add(new Cityrow("n2_c2","Apartamento 2","Precio: 1000", R.drawable.n2_c2));
        listItems.add(new Cityrow("n3_c1","Edificio 1","Precio: 3000", R.drawable.n3_c1));
        listItems.add(new Cityrow("n3_c2","Edificio 2","Precio: 5000", R.drawable.n3_c2));



        return listItems;
    }




}