package com.example.city.Inicio;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.city.GanarActivity;
import com.example.city.Modelos.MainActivity;
import com.example.city.R;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class RegistroActivity extends AppCompatActivity {

    String uidAgregar, fotoUrl;
    TextView  uid;
    EditText nickname, nombre, correo, ciudad;
    Button logout;
    Spinner sp_institucion;
    ImageView fotoPerfil;
    Button registro;

    private List<Usuario> listUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;

    //Variables para agregar a firebase
   FirebaseDatabase firebaseDatabase;
   DatabaseReference databaseReference;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializarFirebase();

       // logout = findViewById(R.id.logout);
        nombre = findViewById(R.id.txtNombre);
        correo = findViewById(R.id.txtCorreo);
        uid = findViewById(R.id.uid);
        sp_institucion = findViewById(R.id.sp_institucion);
        fotoPerfil = (ImageView) findViewById(R.id.fotoPerfil);
        ciudad = (EditText) findViewById(R.id.txtCiudad);
        nickname = (EditText) findViewById(R.id.txtNickname);
        registro = (Button) findViewById(R.id.btnRegistro);



        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null){

            uid.setText(signInAccount.getId());
            uidAgregar = signInAccount.getId();
            nombre.setText(signInAccount.getDisplayName());
            correo.setText(signInAccount.getEmail());
            fotoUrl = String.valueOf(signInAccount.getPhotoUrl());
            //consultarUsuario();


            //cargar photo
            try{
                Glide.with(this)
                        .load(String.valueOf(signInAccount.getPhotoUrl()))
                        .into(fotoPerfil);
            }catch (Exception e){

            }



        }

        /*
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),InicioSesion.class);
                startActivity(intent);

            }
        });

         */



        //FUNCIONES DEL SPINNER
        //dando los tiempos para el spinner
        sp_institucion = (Spinner) findViewById(R.id.sp_institucion);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.institucion_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp_institucion.setAdapter(adapter);




        //accion de spinner
        sp_institucion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Este codigo es para el evento que indique cual tiempo se esta seleccionando
               // tiempoSeleccionado = sp_tiempo.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        //le damos al boton agregar
        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    public void registrar(){

        String vnombre = nombre.getText().toString();

        if(vnombre.equals("") || nickname.getText().toString().equals("") || correo.getText().toString().equals("") || ciudad.getText().toString().equals("")){
            validacion();
        }else{
            //Para poner un random UID
            //UUID.randomUUID().toString());

            Usuario usuario = new Usuario();
            usuario.setUid(uidAgregar);
            usuario.setNickname(nickname.getText().toString());
            usuario.setNombre(nombre.getText().toString());
            usuario.setCorreo(correo.getText().toString());
            usuario.setInstitucion(sp_institucion.getSelectedItem().toString());
            usuario.setIdCiudad("city"+uidAgregar);
            usuario.setPuntosGanar(0);
            usuario.setPuntosPerder(0);
            usuario.setMonedas(0);
            usuario.setFotoUrl(fotoUrl);

            //agregar datos a la ciudad del usuario
            Ciudad c = new Ciudad();
            c.setUid("city"+uidAgregar);
            c.setNombre(ciudad.getText().toString());
            c.setIdUsuario(uidAgregar);
            c.setXp(0);

            //colocando edificios
            c.setA1("0");
            c.setA2("0");
            c.setA3("0");
            c.setA4("0");
            c.setA5("0");
            c.setB1("0");
            c.setB2("0");
            c.setB3("0");
            c.setB4("0");
            c.setB5("0");
            c.setC1("0");
            c.setC2("0");
            c.setC3("0");
            c.setC4("0");
            c.setC5("0");

            //Agregar datos child

            databaseReference.child("Usuario").child(usuario.getUid()).setValue(usuario);

            databaseReference.child("Ciudad").child(c.getUid()).setValue(c);

            Toast.makeText(this,"Agregado",Toast.LENGTH_SHORT).show();

            goMainScreen();

        }//FINALIZA ELSE DE AGREGAR


    }

    private void validacion() {

       String vnombre = nombre.getText().toString();
       if(vnombre.equals("")){
           nombre.setError("Required");
       }
       if(nickname.getText().toString().equals("")){
           nickname.setError("Required");
       }
        if(correo.getText().toString().equals("")){
            correo.setError("Required");
        }
        if(ciudad.getText().toString().equals("")){
            ciudad.setError("Required");
        }


    }

    private void goMainScreen(){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }




    private void consultarUsuario(){

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listUsuario.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);
                    listUsuario.add(u);

                   if (u.getUid().equals(uidAgregar)){
                      // goMainScreen();
                   }
                    //ESTE METODO SERVIRA PARA CONSULTA EN FUTURAS LISTAS

                   // arrayAdapterUsuario = new ArrayAdapter<Usuario>(this, android.R.layout.simple_list_item_1, listUsuario);
                   // listaPersonaID.setAdaptar(ArrayAdapterUsuario);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
