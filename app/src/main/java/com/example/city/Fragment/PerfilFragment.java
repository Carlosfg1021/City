package com.example.city.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.city.Inicio.InicioSesion;
import com.example.city.R;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.uidUsuario;
import static com.example.city.Modelos.MainActivity.user;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View view; //esto es para hacer acciones el perfilFragment

    //para consultas con firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    int ganar=0;
    int perder=0;


    //DECLARACION DE OBJETOS, ETC

    TextView lblNickname, lblCorreo, txtNickname, txtNombre, txtInstitucion, txtCorreo, txtCiudad, txtGanadas, txtPerdidas, txtExp, txtMonedas;
    View fotoPerfilF;

    //Grafica
    PieChart pieChart;

    //Cerrar sesion
    Button logout;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_perfil, container, false);


        lblNickname = (TextView) view.findViewById(R.id.lblNickname);
        lblCorreo = (TextView) view.findViewById(R.id.lblCorreo);
        txtNickname = (TextView) view.findViewById(R.id.txtNickname);
        txtNombre = (TextView) view.findViewById(R.id.txtNombre);
        txtInstitucion = (TextView) view.findViewById(R.id.txtInstitucion);
        txtCorreo = (TextView) view.findViewById(R.id.txtCorreo);
        txtCiudad = (TextView) view.findViewById(R.id.txtCiudad);
        txtGanadas = (TextView) view.findViewById(R.id.txtGanadas);
        txtPerdidas = (TextView) view.findViewById(R.id.txtPerdidas);
        txtExp = (TextView) view.findViewById(R.id.txtExp);
        txtMonedas = (TextView) view.findViewById(R.id.txtMonedas);
        fotoPerfilF = (ImageView) view.findViewById(R.id.fotoPerfilF);
        pieChart = view.findViewById(R.id.graficoPastel);
        logout = view.findViewById(R.id.logout);

        inicializarFirebase();
        mostrarPerfil();
        mostrarCiudadExp();
        //crearGraficoPastel(ganar, perder);
        crearGrafico();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), InicioSesion.class);
                startActivity(intent);
            }
        });



        return view;


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getContext());
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
                        //CARGAR CIUDAD
                        txtGanadas.setText(String.valueOf(u.getPuntosGanar()));
                        txtPerdidas.setText(String.valueOf(u.getPuntosPerder()));
                        //CARGAR EXPERIENCIA

                        txtMonedas.setText(String.valueOf(u.getMonedas()));

                        ganar = u.getPuntosGanar();
                        perder = u.getPuntosPerder();


                        //cargar photo
                        try {
                            Glide.with(getContext())
                                    .load(u.getFotoUrl())
                                    .into((ImageView) fotoPerfilF);
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
                        txtExp.setText(String.valueOf(c.getXp()));

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
