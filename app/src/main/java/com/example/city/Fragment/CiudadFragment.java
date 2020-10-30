package com.example.city.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import static com.facebook.FacebookSdk.getApplicationContext;

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

    Ciudad fcity = new Ciudad();

    private ImageView a1,a2,a3,a4,a5, b1,b2,b3, c1,c2,c3,c4;

    Drawable prueba;

    Context context;


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

        a1 = (ImageView) view.findViewById(R.id.img_a1);
        a2 = (ImageView) view.findViewById(R.id.img_a2);
        a3 = (ImageView) view.findViewById(R.id.img_a3);
        a4 = (ImageView) view.findViewById(R.id.img_a4);
        a5 = (ImageView) view.findViewById(R.id.img_a5);

        b1 = (ImageView) view.findViewById(R.id.img_b1);
        b2 = (ImageView) view.findViewById(R.id.img_b2);
        b3 = (ImageView) view.findViewById(R.id.img_b3);

        c1 = (ImageView) view.findViewById(R.id.img_c1);
        c2 = (ImageView) view.findViewById(R.id.img_c2);
        c3 = (ImageView) view.findViewById(R.id.img_c3);
        c4 = (ImageView) view.findViewById(R.id.img_c4);





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

    private void cargarCiudad(Ciudad city ){

        //Para la posicion A1
        if(city.getA1().equals("n1_c1")){
            a1.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getA1().equals("n1_c2")){
            a1.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getA1().equals("n1_c3")){
            a1.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getA1().equals("n2_c1")){
            a1.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getA1().equals("n2_c2")){
            a1.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getA1().equals("n3_c1")){
            a1.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getA1().equals("n3_c2")){
            a1.setBackgroundResource(R.drawable.n3_c2);
        }

        //para la posición A2

        if(city.getA2().equals("n1_c1")){
            a2.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getA2().equals("n1_c2")){
            a2.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getA2().equals("n1_c3")){
            a2.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getA2().equals("n2_c1")){
            a2.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getA2().equals("n2_c2")){
            a2.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getA2().equals("n3_c1")){
            a2.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getA2().equals("n3_c2")){
            a2.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion A3

        if(city.getA3().equals("n1_c1")){
            a3.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getA3().equals("n1_c2")){
            a3.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getA3().equals("n1_c3")){
            a3.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getA3().equals("n2_c1")){
            a3.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getA3().equals("n2_c2")){
            a3.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getA3().equals("n3_c1")){
            a3.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getA3().equals("n3_c2")){
            a3.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion A4

        if(city.getA4().equals("n1_c1")){
            a4.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getA4().equals("n1_c2")){
            a4.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getA4().equals("n1_c3")){
            a4.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getA4().equals("n2_c1")){
            a4.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getA4().equals("n2_c2")){
            a4.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getA4().equals("n3_c1")){
            a4.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getA4().equals("n3_c2")){
            a4.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion A5

        if(city.getA5().equals("n1_c1")){
            a5.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getA5().equals("n1_c2")){
            a5.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getA5().equals("n1_c3")){
            a5.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getA5().equals("n2_c1")){
            a5.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getA5().equals("n2_c2")){
            a5.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getA5().equals("n3_c1")){
            a5.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getA5().equals("n3_c2")){
            a5.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion B1

        if(city.getB1().equals("n1_c1")){
            b1.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getB1().equals("n1_c2")){
            b1.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getB1().equals("n1_c3")){
            b1.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getB1().equals("n2_c1")){
            b1.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getB1().equals("n2_c2")){
            b1.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getB1().equals("n3_c1")){
            b1.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getB1().equals("n3_c2")){
            b1.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion B2

        if(city.getB2().equals("n1_c1")){
            b2.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getB2().equals("n1_c2")){
            b2.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getB2().equals("n1_c3")){
            b2.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getB2().equals("n2_c1")){
            b2.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getB2().equals("n2_c2")){
            b2.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getB2().equals("n3_c1")){
            b2.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getB2().equals("n3_c2")){
            b2.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion B3

        if(city.getB3().equals("n1_c1")){
            b3.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getB3().equals("n1_c2")){
            b3.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getB3().equals("n1_c3")){
            b3.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getB3().equals("n2_c1")){
            b3.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getB3().equals("n2_c2")){
            b3.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getB3().equals("n3_c1")){
            b3.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getB3().equals("n3_c2")){
            b3.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion C1

        if(city.getC1().equals("n1_c1")){
            c1.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getC1().equals("n1_c2")){
            c1.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getC1().equals("n1_c3")){
            c1.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getC1().equals("n2_c1")){
            c1.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getC1().equals("n2_c2")){
            c1.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getC1().equals("n3_c1")){
            c1.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getC1().equals("n3_c2")){
            c1.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion C2
        if(city.getC2().equals("n1_c1")){
            c2.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getC2().equals("n1_c2")){
            c2.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getC2().equals("n1_c3")){
            c2.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getC2().equals("n2_c1")){
            c2.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getC2().equals("n2_c2")){
            c2.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getC2().equals("n3_c1")){
            c2.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getC2().equals("n3_c2")){
            c2.setBackgroundResource(R.drawable.n3_c2);
        }

        //Para la posicion C3
        if(city.getC3().equals("n1_c1")){
            c3.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getC3().equals("n1_c2")){
            c3.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getC3().equals("n1_c3")){
            c3.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getC3().equals("n2_c1")){
            c3.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getC3().equals("n2_c2")){
            c3.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getC3().equals("n3_c1")){
            c3.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getC3().equals("n3_c2")){
            c3.setBackgroundResource(R.drawable.n3_c2);
        }


        //Para la posicion C4
        if(city.getC4().equals("n1_c1")){
            c4.setBackgroundResource(R.drawable.n1_c1);
        }else if(city.getC4().equals("n1_c2")){
            c4.setBackgroundResource(R.drawable.n1_c2);
        } else if(city.getC4().equals("n1_c3")){
            c4.setBackgroundResource(R.drawable.n1_c3);
        } else if(city.getC4().equals("n2_c1")){
            c4.setBackgroundResource(R.drawable.n2_c1);
        }else if(city.getC4().equals("n2_c2")){
            c4.setBackgroundResource(R.drawable.n2_c2);
        }else if(city.getC4().equals("n3_c1")){
            c4.setBackgroundResource(R.drawable.n3_c1);
        }else if(city.getC4().equals("n3_c2")){

            c4.setBackgroundResource(R.drawable.n3_c2);
        }








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


                        fcity.setA1(c.getA1());
                        fcity.setA2(c.getA2());
                        fcity.setA3(c.getA3());
                        fcity.setA4(c.getA4());
                        fcity.setA5(c.getA5());

                        fcity.setB1(c.getB1());
                        fcity.setB2(c.getB2());
                        fcity.setB3(c.getB3());
                        fcity.setB4(c.getB4());
                        fcity.setB5(c.getB5());

                        fcity.setC1(c.getC1());
                        fcity.setC2(c.getC2());
                        fcity.setC3(c.getC3());
                        fcity.setC4(c.getC4());
                        fcity.setC5(c.getC5());

                        break;

                    }else{

                    }

                }

                cargarCiudad(fcity);






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }






}