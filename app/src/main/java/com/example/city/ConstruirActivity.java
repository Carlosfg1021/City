package com.example.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.Adaptadores.AdaptadorCiudad;
import com.example.city.Fragment.CiudadFragment;
import com.example.city.Modelos.MainActivity;
import com.example.city.datos.Cityrow;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.uidUsuario;
import static com.example.city.Modelos.MainActivity.user;

public class ConstruirActivity extends AppCompatActivity {


    private ListView lvItems;
    private AdaptadorCiudad adaptador;
    private TextView txtEdificioSeleccionado;
    private TextView txtPrecioSeleccionado;
    private TextView txtIdSeleccionado;
    private ImageView imgEdificioSeleccionado;
    private TextView lblMonedas, lblXp;
    private int bandera1=0, bandera2=0;
    private TextView pEdificio;
    private int banderaConstruccion=0;




    private RadioButton a1, a2, a3, a4, a5, b1, b2, b3, c1, c2, c3, c4;
    private TextView posicionSeleccionada;
    private Button btnComprar;

    Button btnRegresar;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Ciudad city = new Ciudad();
    private Usuario userC = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_construir);

    lvItems = findViewById(R.id.listaEdificios);
    txtEdificioSeleccionado = findViewById(R.id.edificioSeleccionado);
    txtPrecioSeleccionado = findViewById(R.id.precioSeleccionado);
    txtIdSeleccionado = findViewById(R.id.idEdificioSeleccionado);
    imgEdificioSeleccionado = (ImageView) findViewById(R.id.imgEdificioSeleccionado);
    posicionSeleccionada = (TextView) findViewById(R.id.posicionSeleccionada);
    lblMonedas = (TextView) findViewById(R.id.lblMonedas);
    lblXp = (TextView) findViewById(R.id.lblXp);
    pEdificio = (TextView) findViewById(R.id.pEdificio);

         a1 = (RadioButton) findViewById(R.id.rbtn_a1);
        a2 = (RadioButton) findViewById(R.id.rbtn_a2);
        a3 = (RadioButton) findViewById(R.id.rbtn_a3);
        a4 = (RadioButton) findViewById(R.id.rbtn_a4);
        a5 = (RadioButton) findViewById(R.id.rbtn_a5);

        b1 = (RadioButton) findViewById(R.id.rbtn_b1);
        b2 = (RadioButton) findViewById(R.id.rbtn_b2);
        b3 = (RadioButton) findViewById(R.id.rbtn_b3);

        c1 = (RadioButton) findViewById(R.id.rbtn_c1);
        c2 = (RadioButton) findViewById(R.id.rbtn_c2);
        c3 = (RadioButton) findViewById(R.id.rbtn_c3);
        c4 = (RadioButton) findViewById(R.id.rbtn_c4);

        btnComprar = (Button) findViewById(R.id.btnComprar);
        btnRegresar = (Button) findViewById(R.id.btnRegresar);

        inicializarFirebase();
        accionRadioButton();
        seleccionarCiudad();
        seleccionarMonedas();
        seleccionarUsuario();

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });




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
                    pEdificio.setText("A");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c1);
                    bandera1=1;
                    break;

                case 1:
                    txtEdificioSeleccionado.setText("Casa 1");
                    txtPrecioSeleccionado.setText("250");
                    txtIdSeleccionado.setText("n1_c2");
                    pEdificio.setText("A");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c2);
                    bandera1=1;
                    break;

                case 2:
                    txtEdificioSeleccionado.setText("Casa 2");
                    txtPrecioSeleccionado.setText("250");
                    txtIdSeleccionado.setText("n1_c3");
                    pEdificio.setText("A");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n1_c3);
                    bandera1=1;

                    break;

                case 3:
                    txtEdificioSeleccionado.setText("Apartamento 1");
                    txtPrecioSeleccionado.setText("700");
                    txtIdSeleccionado.setText("n2_c1");
                    pEdificio.setText("B");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n2_c1);
                    bandera1=1;

                    break;

                case 4:
                    txtEdificioSeleccionado.setText("Apartamento 2");
                    txtPrecioSeleccionado.setText("1000");
                    txtIdSeleccionado.setText("n2_c2");
                    pEdificio.setText("B");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n2_c2);
                    bandera1=1;

                    break;

                case 5:
                    txtEdificioSeleccionado.setText("Edificio 1");
                    txtPrecioSeleccionado.setText("3000");
                    txtIdSeleccionado.setText("n3_c1");
                    pEdificio.setText("C");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n3_c1);
                    bandera1=1;

                    break;

                case 6:
                    txtEdificioSeleccionado.setText("Edificio 2");
                    txtPrecioSeleccionado.setText("5000");
                    txtIdSeleccionado.setText("n3_c2");
                    pEdificio.setText("C");
                    imgEdificioSeleccionado.setImageResource(R.drawable.n3_c2);
                    bandera1=1;

                    break;
            }


        }
    });


    btnComprar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            comprarEdificio();
        }
    });




    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private ArrayList<Cityrow> getArrayItems(){

        ArrayList<Cityrow> listItems = new ArrayList<>();

        listItems.add(new Cityrow("n1_c1","Garage","Precio: 200", R.drawable.n1_c1, "A"));
        listItems.add(new Cityrow("n1_c2","Casa 1","Precio: 250", R.drawable.n1_c2, "A"));
        listItems.add(new Cityrow("n1_c3","Casa 2","Precio: 250", R.drawable.n1_c3, "A"));
        listItems.add(new Cityrow("n2_c1","Apartamento 1","Precio: 700", R.drawable.n2_c1, "B"));
        listItems.add(new Cityrow("n2_c2","Apartamento 2","Precio: 1000", R.drawable.n2_c2, "B"));
        listItems.add(new Cityrow("n3_c1","Edificio 1","Precio: 3000", R.drawable.n3_c1, "C"));
        listItems.add(new Cityrow("n3_c2","Edificio 2","Precio: 5000", R.drawable.n3_c2, "C"));



        return listItems;
    }


    private void comprarEdificio(){
        banderaConstruccion=0;

        if(bandera1==0 || bandera2==0){
            Toast.makeText(this, "Debes seleccionar un edificio y una posición", Toast.LENGTH_SHORT).show();
        }else{
            String codigoEdificio = String.valueOf(txtIdSeleccionado.getText());
            String posicion = String.valueOf(posicionSeleccionada.getText());
            String clase = String.valueOf(pEdificio.getText());
            int monedas = user.getMonedas();
            int precio = Integer.parseInt(txtPrecioSeleccionado.getText().toString());


            if(monedas >= precio){

                if(clase.equals("A")){

                    if(posicion.equals("a1")){
                        city.setA1(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("a2")){
                        city.setA2(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("a3")){
                        city.setA3(codigoEdificio);
                        banderaConstruccion=1;
                    }
                    else if(posicion.equals("a4")){
                        city.setA4(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("a5")){
                        city.setA5(codigoEdificio);
                        banderaConstruccion=1;
                    }

                }else{



                }


                if(clase.equals("B")){

                    if(posicion.equals("b1")){
                        city.setB1(codigoEdificio);
                        banderaConstruccion=1;
                    } else if(posicion.equals("b2")){
                        city.setB2(codigoEdificio);
                        banderaConstruccion=1;
                    } else if(posicion.equals("b3")){
                        city.setB3(codigoEdificio);
                        banderaConstruccion=1;
                    }

                }else{

                }



                if(clase.equals("C")){

                    if(posicion.equals("c1")){
                        city.setC1(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("c2")){
                        city.setC2(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("c3")){
                        city.setC3(codigoEdificio);
                        banderaConstruccion=1;
                    }else if(posicion.equals("c4")){
                        city.setC4(codigoEdificio);
                        banderaConstruccion=1;
                    }


                }else{


                }



                if(banderaConstruccion==1){
                    databaseReference.child("Ciudad").child(city.getUid()).setValue(city);
                    int monedasAnteriores = user.getMonedas();
                    int monedasActuales = monedasAnteriores - precio;
                    userC.setMonedas(monedasActuales);
                    databaseReference.child("Usuario").child(userC.getUid()).setValue(userC);
                    lblMonedas.setText(""+monedasActuales);

                    Toast.makeText(this, "Edificio construido exitosamente", Toast.LENGTH_SHORT).show();

                }else{
                    if(banderaConstruccion==0){
                        Toast.makeText(this, "No puedes colocar este edificio aquí", Toast.LENGTH_SHORT).show();
                    }
                }








            }else{//else para las monedas
                Toast.makeText(this, "Monedas insuficientes", Toast.LENGTH_SHORT).show();
            }





        }


    }


    private void seleccionarMonedas(){
        lblMonedas.setText(""+user.getMonedas());

    }

    private void seleccionarUsuario() {
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Usuario u = objSnapshot.getValue(Usuario.class);

                    if (u.getUid().equals(uidUsuario)) {
                        userC.setUid(u.getUid());
                        userC.setCorreo(u.getCorreo());
                        userC.setNickname(u.getNickname());
                        userC.setNombre(u.getNombre());
                        userC.setInstitucion(u.getInstitucion());
                        //CARGAR CIUDAD


                        userC.setPuntosGanar(u.getPuntosGanar());
                        userC.setPuntosPerder(u.getPuntosPerder());
                        //CARGAR EXPERIENCIA
                        userC.setMonedas(u.getMonedas());
                        userC.setFotoUrl(u.getFotoUrl());
                        userC.setIdCiudad(u.getIdCiudad());


                        break;

                    } else {


                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      }


        private void seleccionarCiudad(){
        databaseReference.child("Ciudad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Ciudad c = objSnapshot.getValue(Ciudad.class);

                    if (c.getUid().equals(uidCiudad)){
                        city.setUid(c.getUid());
                        city.setNombre(c.getNombre());
                        city.setXp(c.getXp());
                        lblXp.setText(""+c.getXp());


                        city.setA1(c.getA1());
                        city.setA2(c.getA2());
                        city.setA3(c.getA3());
                        city.setA4(c.getA4());
                        city.setA5(c.getA5());

                        city.setB1(c.getB1());
                        city.setB2(c.getB2());
                        city.setB3(c.getB3());
                        city.setB4(c.getB4());
                        city.setB5(c.getB5());

                        city.setC1(c.getC1());
                        city.setC2(c.getC2());
                        city.setC3(c.getC3());
                        city.setC4(c.getC4());
                        city.setC5(c.getC5());

                        break;

                    }else{

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }






    private void accionRadioButton(){

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("a1");
                a1.setChecked(true);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("a2");
                a1.setChecked(false);
                a2.setChecked(true);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("a3");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(true);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("a4");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(true);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("a5");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(true);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });


        //ahora vamos con los b

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("b1");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(true);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("b2");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(true);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("b3");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(true);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        //ahora vamos con las c


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("c1");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(true);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("c2");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(true);
                c3.setChecked(false);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("c3");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(true);
                c4.setChecked(false);
                bandera2 = 1;


            }
        });

        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posicionSeleccionada.setText("c4");
                a1.setChecked(false);
                a2.setChecked(false);
                a3.setChecked(false);
                a4.setChecked(false);
                a5.setChecked(false);

                b1.setChecked(false);
                b2.setChecked(false);
                b3.setChecked(false);

                c1.setChecked(false);
                c2.setChecked(false);
                c3.setChecked(false);
                c4.setChecked(true);
                bandera2 = 1;


            }
        });


    }

}