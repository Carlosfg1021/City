package com.example.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.Modelos.MainActivity;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static com.example.city.Modelos.MainActivity.city;
import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.uidUsuario;
import static com.example.city.Modelos.MainActivity.user;

public class GanarActivity extends AppCompatActivity {

    String opcion;
    String tiempoSeleccionado;
    TextView titulo;
    TextView cantidadxp;
    ImageView fondo;
    Button btncontinuar;
    TextView lblMonedas;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Usuario u2 = new Usuario();
    Ciudad c2 = new Ciudad();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganar);
        Bundle bundle = getIntent().getExtras();
        opcion = bundle.getString("opcion");
        tiempoSeleccionado = bundle.getString("tiempo");
        titulo = (TextView) findViewById(R.id.titulo);
        cantidadxp = (TextView) findViewById(R.id.cantidadxp);
        lblMonedas = (TextView) findViewById(R.id.lblMonedas);
        fondo = (ImageView) findViewById(R.id.fondo);
        btncontinuar = (Button) findViewById(R.id.btnContinuar);


        inicializarFirebase();
        seleccionarUsuario();
        seleccionarCiudad();






        if(opcion.equals("ganar")){
           // Toast.makeText(this, tiempoSeleccionado, Toast.LENGTH_SHORT).show();
            titulo.setText("¡Ganaste!");


            if(tiempoSeleccionado.equals("5 segundos")) {
                cantidadxp.setText("+25XP");
                lblMonedas.setText("+25");

            }else if(tiempoSeleccionado.equals("1 minuto")){
                cantidadxp.setText("+5XP");
                lblMonedas.setText("+5");
                //monedas: 5
            }else if(tiempoSeleccionado.equals("5 minutos")){

                cantidadxp.setText("+10XP");
                lblMonedas.setText("+10");
                //MONEDAS: 10
            }else if(tiempoSeleccionado.equals("15 minutos")){

                cantidadxp.setText("+50XP");
                lblMonedas.setText("+50");
                //MONEDAS: 50

            }
            else if(tiempoSeleccionado.equals("30 minutos")){
                cantidadxp.setText("+100XP");
                lblMonedas.setText("+100");
                //MONEDAS:100

            }
            else if(tiempoSeleccionado.equals("45 minutos")){

                cantidadxp.setText("+150XP");
                lblMonedas.setText("+150");
                //MONEDAS:150

            }
            else if(tiempoSeleccionado.equals("1 hora")){
                cantidadxp.setText("+250XP");
                lblMonedas.setText("+250");
                //monedas: 250


            }
            else if(tiempoSeleccionado.equals("2 horas")){
                cantidadxp.setText("+700XP");
                lblMonedas.setText("+700");
                //monedas: 700
            }

            Toast.makeText(this, city.getNombre(), Toast.LENGTH_SHORT).show();


        }

        if(opcion.equals("perder")){
           // Toast.makeText(this, "MENOS PUNTOS", Toast.LENGTH_SHORT).show();
            titulo.setText("¡Perdiste!");
            cantidadxp.setText("-pendienteXP");
            fondo.setBackgroundColor(Color.rgb(252, 91, 91));

            if(tiempoSeleccionado.equals("5 segundos")) {

                lblMonedas.setText("-25");

            }else if(tiempoSeleccionado.equals("1 minuto")){

                lblMonedas.setText("-5");
                //monedas: 5
            }else if(tiempoSeleccionado.equals("5 minutos")){


                lblMonedas.setText("-10");
                //MONEDAS: 10
            }else if(tiempoSeleccionado.equals("15 minutos")){


                lblMonedas.setText("-30");
                //MONEDAS: 30

            }
            else if(tiempoSeleccionado.equals("30 minutos")){

                lblMonedas.setText("-50");
                //MONEDAS:50

            }
            else if(tiempoSeleccionado.equals("45 minutos")){
                lblMonedas.setText("-70");
                //MONEDAS:70
            }
            else if(tiempoSeleccionado.equals("1 hora")){
                lblMonedas.setText("-100");
                //monedas: -100
            }
            else if(tiempoSeleccionado.equals("2 horas")){

                lblMonedas.setText("-200");
                //monedas: -200
            }


        }

        btncontinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }
        });





    }

    @Override
    public void onStop() {
        super.onStop();

        if(opcion.equals("ganar")){


            if(tiempoSeleccionado.equals("5 segundos")) {
                //cantidadxp.setText("+25XP");
                sumarGanar(25,25);

            }else if(tiempoSeleccionado.equals("1 minuto")){
               // cantidadxp.setText("+5XP");
                //monedas: 5
                sumarGanar(5,5);
            }else if(tiempoSeleccionado.equals("5 minutos")){

               // cantidadxp.setText("+10XP");
                //MONEDAS: 10
                sumarGanar(10,10);
            }else if(tiempoSeleccionado.equals("15 minutos")){
              //  cantidadxp.setText("+50XP");
                //MONEDAS: 50
                sumarGanar(50,50);

            }
            else if(tiempoSeleccionado.equals("30 minutos")){
              //  cantidadxp.setText("+100XP");
                //MONEDAS:100
                sumarGanar(100,100);

            }
            else if(tiempoSeleccionado.equals("45 minutos")){
                //cantidadxp.setText("+150XP");
                //MONEDAS:150
                sumarGanar(150,150);

            }
            else if(tiempoSeleccionado.equals("1 hora")){
                //cantidadxp.setText("+250XP");
                //monedas: 250
                sumarGanar(250,250);

            }
            else if(tiempoSeleccionado.equals("2 horas")){
                //cantidadxp.setText("+700XP");
                //monedas: 700
                sumarGanar(700,700);
            }

            //----------PERDER-------------------

        }else if (opcion.equals("perder")){



            if(tiempoSeleccionado.equals("5 segundos")) {
                //cantidadxp.setText("+25XP");
                sumarPerder(25);

            }else if(tiempoSeleccionado.equals("1 minuto")){
                // cantidadxp.setText("+5XP");
                //monedas: 5
                sumarPerder(5);
            }else if(tiempoSeleccionado.equals("5 minutos")){

                // cantidadxp.setText("+10XP");
                //MONEDAS: 10
                sumarPerder(10);
            }else if(tiempoSeleccionado.equals("15 minutos")){
                //  cantidadxp.setText("+50XP");
                //MONEDAS: 50
                sumarPerder(30);

            }
            else if(tiempoSeleccionado.equals("30 minutos")){
                //  cantidadxp.setText("+100XP");
                //MONEDAS:100
                sumarPerder(50);

            }
            else if(tiempoSeleccionado.equals("45 minutos")){
                //cantidadxp.setText("+150XP");
                //MONEDAS:150
                sumarPerder(70);

            }
            else if(tiempoSeleccionado.equals("1 hora")){
                //cantidadxp.setText("+250XP");
                //monedas: 250
                sumarPerder(100);

            }
            else if(tiempoSeleccionado.equals("2 horas")){
                //cantidadxp.setText("+700XP");
                //monedas: 700
                sumarPerder(200);
            }


        }


    }

    public void sumarGanar(int monedasGanar, int experienciaGanar){
        int puntosAnterior = u2.getPuntosGanar();
        int puntosActual = puntosAnterior + 1;
        int monedasAnterior = u2.getMonedas();
        int monedasActual = monedasAnterior + monedasGanar;
        u2.setPuntosGanar(puntosActual);
        u2.setMonedas(monedasActual);
        databaseReference.child("Usuario").child(u2.getUid()).setValue(u2);

        int experienciaAnterior = c2.getXp();
        int expericenciaActual = experienciaAnterior + experienciaGanar;
        c2.setXp(expericenciaActual);

        databaseReference.child("Ciudad").child(c2.getUid()).setValue(c2);

    }

    public void sumarPerder(int monedasPerder){
        int puntosAnterior = u2.getPuntosPerder();
        int puntosActual = puntosAnterior + 1;

        int monedasAnterior = u2.getMonedas();
        int monedasActual = monedasAnterior - monedasPerder;
        if(monedasActual<=0){
            monedasActual=0;
        }
        u2.setMonedas(monedasActual);
        u2.setPuntosPerder(puntosActual);
        databaseReference.child("Usuario").child(u2.getUid()).setValue(u2);
    }
    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void sumarExperiencia(){
        Usuario u = new Usuario();
        u.setUid(uidUsuario);

    }

    private void seleccionarUsuario(){
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                     Usuario u = objSnapshot.getValue(Usuario.class);

                     if (u.getUid().equals(uidUsuario)){


                        u2.setUid(u.getUid());
                        u2.setCorreo(u.getCorreo());
                        u2.setNickname(u.getNickname());
                        u2.setNombre(u.getNombre());
                        u2.setInstitucion(u.getInstitucion());
                        //CARGAR CIUDAD


                        u2.setPuntosGanar(u.getPuntosGanar());
                        u2.setPuntosPerder(u.getPuntosPerder());
                        //CARGAR EXPERIENCIA
                        u2.setMonedas(u.getMonedas());
                        u2.setFotoUrl(u.getFotoUrl());
                        u2.setIdCiudad(u.getIdCiudad());





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

    private void seleccionarCiudad(){
        databaseReference.child("Ciudad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Ciudad c = objSnapshot.getValue(Ciudad.class);

                    if (c.getUid().equals(uidCiudad)){


                        c2.setUid(c.getUid());
                        c2.setNombre(c.getNombre());
                        c2.setIdUsuario(c.getIdUsuario());
                        c2.setXp(c.getXp());

                        c2.setA1(c.getA1());
                        c2.setA2(c.getA2());
                        c2.setA3(c.getA3());
                        c2.setA4(c.getA4());
                        c2.setA5(c.getA5());

                        c2.setB1(c.getB1());
                        c2.setB2(c.getB2());
                        c2.setB3(c.getB3());
                        c2.setB4(c.getB4());
                        c2.setB5(c.getB5());

                        c2.setC1(c.getC1());
                        c2.setC2(c.getC2());
                        c2.setC3(c.getC3());
                        c2.setC4(c.getC4());
                        c2.setC5(c.getC5());


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





}