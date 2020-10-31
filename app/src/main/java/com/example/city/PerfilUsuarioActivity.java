package com.example.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

    private Button btnVisitar;

    public static String idUsuarioConsulta;
     public static String idCiudadConsulta;

    //para consultas con firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_perfil_usuario);
        lblNickname = (TextView) findViewById(R.id.lblNicknameC);
        txtNickname = (TextView) findViewById(R.id.txtNicknameC);
        txtNombre = (TextView) findViewById(R.id.txtNombreC);
        txtInstitucion = (TextView) findViewById(R.id.txtInstitucionC);
        txtCorreo = (TextView) findViewById(R.id.txtCorreoC);
        lblCorreo = (TextView) findViewById(R.id.lblCorreoC);
        txtCiudad = (TextView) findViewById(R.id.txtCiudadC);
        txtGanadas = (TextView) findViewById(R.id.txtGanadasC);
        txtPerdidas = (TextView) findViewById(R.id.txtPerdidasC);
        txtXp = (TextView) findViewById(R.id.txtExpC);
        btnVisitar = (Button) findViewById(R.id.btnVisitarCiudad);

        imgFoto = (ImageView) findViewById(R.id.fotoPerfilC);
        pieChart = findViewById(R.id.graficoPastelC);

       idUsuarioConsulta = bundle.getString("idcardview");

        /*databaseReference = FirebaseDatabase.getInstance().getReference().child("Usuario");
        String Carkey = getIntent().getStringExtra("Carkey");

        databaseReference.child(Carkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){


                    String nombre = dataSnapshot.child("nombre").getValue().toString();
                    txtNombre.setText(nombre);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        inicializarFirebase();
        mostrarPerfil();
        mostrarCiudadExp();
        crearGraficoPastel(ganar, perder);
        crearGrafico();

        btnVisitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CiudadUsuarioActivity.class);
              //  intent.putExtra("idCiudad", idCiudadConsulta);
                startActivity(intent);

            }
        });


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

                    if (u.getUid().equals(idUsuarioConsulta)) {
                        //TextView lblNickname, lblCorreo, txtNickname, txtNombre, txtInstitucion, txtCorreo, txtCiudad, txtGanadas, txtPerdidas, txtExp, txtMonedas;
                        lblNickname.setText(u.getNickname());
                        lblCorreo.setText(u.getCorreo());
                        txtNickname.setText(u.getNickname());
                        txtNombre.setText(u.getNombre());
                        txtInstitucion.setText(u.getInstitucion());
                        txtCorreo.setText(u.getCorreo());

                        idCiudadConsulta = u.getIdCiudad();

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

                    if (c.getUid().equals(idCiudadConsulta)) {
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

                    if (u.getUid().equals(idUsuarioConsulta)) {




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