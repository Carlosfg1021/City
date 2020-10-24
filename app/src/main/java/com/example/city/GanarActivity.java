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
import com.example.city.datos.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static com.example.city.Modelos.MainActivity.city;
import static com.example.city.Modelos.MainActivity.uidUsuario;
import static com.example.city.Modelos.MainActivity.user;

public class GanarActivity extends AppCompatActivity {

    String opcion;
    TextView titulo;
    TextView cantidadxp;
    ImageView fondo;
    Button btncontinuar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Usuario u2 = new Usuario();





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

        inicializarFirebase();
        seleccionarUsuario();






        if(opcion.equals("ganar")){
            //Toast.makeText(this, "MAS PUNTOS", Toast.LENGTH_SHORT).show();
            titulo.setText("¡Ganaste!");
            cantidadxp.setText("+25XP");





            Toast.makeText(this, city.getNombre(), Toast.LENGTH_SHORT).show();




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

    @Override
    public void onStop() {
        super.onStop();

        if(opcion.equals("ganar")){
            sumarGanar();
        }else if (opcion.equals("perder")){
            sumarPerder();
        }


    }

    public void sumarGanar(){
        int puntosAnterior = u2.getPuntosGanar();
        int puntosActual = puntosAnterior + 1;
        u2.setPuntosGanar(puntosActual);
        databaseReference.child("Usuario").child(u2.getUid()).setValue(u2);
    }

    public void sumarPerder(){
        int puntosAnterior = u2.getPuntosPerder();
        int puntosActual = puntosAnterior + 1;
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




}