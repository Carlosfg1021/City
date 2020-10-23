package com.example.city.Inicio;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.city.Modelos.MainActivity;
import com.example.city.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;


public class RegistroActivity extends AppCompatActivity {

    TextView name, mail, uid;
    Button logout;

    @Override
    protected void onCreate (Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        logout = findViewById(R.id.logout);
        name = findViewById(R.id.txtNombre);
        mail = findViewById(R.id.txtCorreo);
        uid = findViewById(R.id.uid);



        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null){

            uid.setText(signInAccount.getId());
            name.setText(signInAccount.getDisplayName());
            mail.setText(signInAccount.getEmail());

        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),InicioSesion.class);
                startActivity(intent);

            }
        });

    }

}
