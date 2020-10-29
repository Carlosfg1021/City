package com.example.city.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.city.ConstruirActivity;
import com.example.city.Inicio.RegistroActivity;
import com.example.city.R;
import com.example.city.datos.Ciudad;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.city.Modelos.MainActivity.city;
import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.user;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CiudadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CiudadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CiudadFragment() {
        // Required empty public constructor
    }

    View view;
    TextView lblNombreCiudad;
    TextView lblXp;
    TextView lblMonedas;
    ImageView btnConstruir;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CiudadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CiudadFragment newInstance(String param1, String param2) {
        CiudadFragment fragment = new CiudadFragment();
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
        view= inflater.inflate(R.layout.fragment_ciudad, container, false);

        lblNombreCiudad = (TextView) view.findViewById(R.id.lblNombreCiudad);
        lblXp = (TextView) view.findViewById(R.id.lblXp);
        lblMonedas = (TextView) view.findViewById(R.id.lblMonedas);
        btnConstruir = (ImageView) view.findViewById(R.id.btnConstruir);
        inicializarFirebase();
        seleccionarCiudad();






        btnConstruir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConstruirActivity.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        return view;
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    private void seleccionarCiudad(){
        databaseReference.child("Ciudad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Ciudad c = objSnapshot.getValue(Ciudad.class);

                    if (c.getUid().equals(uidCiudad)){
                        lblNombreCiudad.setText(c.getNombre());
                        lblXp.setText("XP: "+c.getXp());
                        lblMonedas.setText("Monedas: " + user.getMonedas());

                        /*
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
                        */
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