package com.example.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.uidUsuario;

public class PerfilUsuarioActivity extends AppCompatActivity {

    private TextView lblNickname, lblCorreo, txtNickname, txtNombre, txtInstitucion, txtCorreo, txtCiudad, txtXp, txtGanadas, txtPerdidas;
    private ImageView imgFoto;
    private PieChart pieChart;
    int ganar, perder;

    //para consultas con firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        lblNickname = (TextView) findViewById(R.id.lblNickname);
        txtNickname = (TextView) findViewById(R.id.txtNickname);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtInstitucion = (TextView) findViewById(R.id.txtInstitucion);
        txtCorreo = (TextView) findViewById(R.id.txtCorreo);
        lblCorreo = (TextView) findViewById(R.id.lblCorreo);
        txtCiudad = (TextView) findViewById(R.id.txtCiudad);
        txtGanadas = (TextView) findViewById(R.id.txtGanadas);
        txtPerdidas = (TextView) findViewById(R.id.txtPerdidas);
        txtXp = (TextView) findViewById(R.id.txtExp);

        imgFoto = (ImageView) findViewById(R.id.fotoPerfilF);
        pieChart = findViewById(R.id.graficoPastel);


        mostrarPerfil();
        mostrarCiudadExp();
        //crearGraficoPastel(ganar, perder);
        crearGrafico();


    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void mostrarPerfil() {

        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Usuario u = objSnapshot.getValue(Usuario.class);

                    if (u.getUid().equals(uidUsuario)) {
                        //TextView lblNickname, lblCorreo, txtNickname, txtNombre, txtInstitucion, txtCorreo, txtCiudad, txtGanadas, txtPerdidas, txtExp, txtMonedas;
                        lblNickname.setText(u.getNickname());
                        lblCorreo.setText(u.getCorreo());
                        txtNickname.setText(u.getNickname());
                        txtNombre.setText(u.getNombre());
                        txtInstitucion.setText(u.getInstitucion());
                        txtCorreo.setText(u.getCorreo());

                        txtGanadas.setText(String.valueOf(u.getPuntosGanar()));
                        txtPerdidas.setText(String.valueOf(u.getPuntosPerder()));
                        //CARGAR EXPERIENCIA

                        ganar = u.getPuntosGanar();
                        perder = u.getPuntosPerder();


                        //cargar photo
                        try {
                            Glide.with(getApplicationContext())
                                    .load(u.getFotoUrl())
                                    .into((ImageView) imgFoto);
                        } catch (Exception e) {

                        }

                    }

                }





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void mostrarCiudadExp() {

        databaseReference.child("Ciudad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Ciudad c = objSnapshot.getValue(Ciudad.class);

                    if (c.getUid().equals(uidCiudad)) {
                        //cargar nombre
                        txtCiudad.setText(c.getNombre());
                        //CARGAR EXPERIENCIA
                        txtXp.setText(String.valueOf(c.getXp()));

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void crearGraficoPastel(int ganar, int perder) {

        Description description = new Description();
        description.setText("Grafica Resultados");
        description.setTextSize(12);

        pieChart.setDescription(description);

        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        pieEntries.add(new PieEntry(ganar));
        pieEntries.add(new PieEntry(perder));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Datos de partidas");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);


    }

    private void crearGrafico(){
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()) {
                    Usuario u = objSnapshot.getValue(Usuario.class);

                    if (u.getUid().equals(uidUsuario)) {




                        Description description = new Description();
                        description.setText("");
                        description.setTextSize(12);

                        pieChart.setDescription(description);

                        ArrayList<PieEntry> pieEntries = new ArrayList<>();

                        pieEntries.add(new PieEntry(u.getPuntosGanar()));
                        pieEntries.add(new PieEntry(u.getPuntosPerder()));

                        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Ganadas y Perdidas");

                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        PieData pieData = new PieData(pieDataSet);
                        pieChart.setNoDataText("Pulsa aquí para ver tu gráfico");
                        pieChart.invalidate();

                        pieChart.setData(pieData);



                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




}