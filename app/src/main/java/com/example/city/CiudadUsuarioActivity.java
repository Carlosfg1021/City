package com.example.city;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.city.datos.Ciudad;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.city.Modelos.MainActivity.uidCiudad;
import static com.example.city.Modelos.MainActivity.user;

public class CiudadUsuarioActivity extends AppCompatActivity {


    TextView lblNombreCiudad;
    TextView lblXp;
    TextView lblMonedas;
    ImageView btnConstruir;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Ciudad fcity = new Ciudad();



    private ImageView a1,a2,a3,a4,a5, b1,b2,b3, c1,c2,c3,c4;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciudad_usuario);
        lblNombreCiudad = (TextView) findViewById(R.id.lblNombreCiudadU);
        lblXp = (TextView) findViewById(R.id.lblXpU);


        a1 = (ImageView) findViewById(R.id.img_a1U);
        a2 = (ImageView) findViewById(R.id.img_a2U);
        a3 = (ImageView) findViewById(R.id.img_a3U);
        a4 = (ImageView) findViewById(R.id.img_a4U);
        a5 = (ImageView) findViewById(R.id.img_a5U);

        b1 = (ImageView) findViewById(R.id.img_b1U);
        b2 = (ImageView) findViewById(R.id.img_b2U);
        b3 = (ImageView) findViewById(R.id.img_b3U);

        c1 = (ImageView) findViewById(R.id.img_c1U);
        c2 = (ImageView) findViewById(R.id.img_c2U);
        c3 = (ImageView) findViewById(R.id.img_c3U);
        c4 = (ImageView) findViewById(R.id.img_c4U);





        inicializarFirebase();
        seleccionarCiudad();



    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cargarCiudad(Ciudad city ){

        //Para la posicion A1
        if(city.getA1().equals("n1_c1")){
            a1.setImageResource(R.drawable.n1_c1);
        }else if(city.getA1().equals("n1_c2")){
            a1.setImageResource(R.drawable.n1_c2);
        } else if(city.getA1().equals("n1_c3")){
            a1.setImageResource(R.drawable.n1_c3);
        } else if(city.getA1().equals("n2_c1")){
            a1.setImageResource(R.drawable.n2_c1);
        }else if(city.getA1().equals("n2_c2")){
            a1.setImageResource(R.drawable.n2_c2);
        }else if(city.getA1().equals("n3_c1")){
            a1.setImageResource(R.drawable.n3_c1);
        }else if(city.getA1().equals("n3_c2")){
            a1.setImageResource(R.drawable.n3_c2);
        }

        //para la posición A2

        if(city.getA2().equals("n1_c1")){
            a2.setImageResource(R.drawable.n1_c1);
        }else if(city.getA2().equals("n1_c2")){
            a2.setImageResource(R.drawable.n1_c2);
        } else if(city.getA2().equals("n1_c3")){
            a2.setImageResource(R.drawable.n1_c3);
        } else if(city.getA2().equals("n2_c1")){
            a2.setImageResource(R.drawable.n2_c1);
        }else if(city.getA2().equals("n2_c2")){
            a2.setImageResource(R.drawable.n2_c2);
        }else if(city.getA2().equals("n3_c1")){
            a2.setImageResource(R.drawable.n3_c1);
        }else if(city.getA2().equals("n3_c2")){
            a2.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion A3

        if(city.getA3().equals("n1_c1")){
            a3.setImageResource(R.drawable.n1_c1);
        }else if(city.getA3().equals("n1_c2")){
            a3.setImageResource(R.drawable.n1_c2);
        } else if(city.getA3().equals("n1_c3")){
            a3.setImageResource(R.drawable.n1_c3);
        } else if(city.getA3().equals("n2_c1")){
            a3.setImageResource(R.drawable.n2_c1);
        }else if(city.getA3().equals("n2_c2")){
            a3.setImageResource(R.drawable.n2_c2);
        }else if(city.getA3().equals("n3_c1")){
            a3.setImageResource(R.drawable.n3_c1);
        }else if(city.getA3().equals("n3_c2")){
            a3.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion A4

        if(city.getA4().equals("n1_c1")){
            a4.setImageResource(R.drawable.n1_c1);
        }else if(city.getA4().equals("n1_c2")){
            a4.setImageResource(R.drawable.n1_c2);
        } else if(city.getA4().equals("n1_c3")){
            a4.setImageResource(R.drawable.n1_c3);
        } else if(city.getA4().equals("n2_c1")){
            a4.setImageResource(R.drawable.n2_c1);
        }else if(city.getA4().equals("n2_c2")){
            a4.setImageResource(R.drawable.n2_c2);
        }else if(city.getA4().equals("n3_c1")){
            a4.setImageResource(R.drawable.n3_c1);
        }else if(city.getA4().equals("n3_c2")){
            a4.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion A5

        if(city.getA5().equals("n1_c1")){
            a5.setImageResource(R.drawable.n1_c1);
        }else if(city.getA5().equals("n1_c2")){
            a5.setImageResource(R.drawable.n1_c2);
        } else if(city.getA5().equals("n1_c3")){
            a5.setImageResource(R.drawable.n1_c3);
        } else if(city.getA5().equals("n2_c1")){
            a5.setImageResource(R.drawable.n2_c1);
        }else if(city.getA5().equals("n2_c2")){
            a5.setImageResource(R.drawable.n2_c2);
        }else if(city.getA5().equals("n3_c1")){
            a5.setImageResource(R.drawable.n3_c1);
        }else if(city.getA5().equals("n3_c2")){
            a5.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion B1

        if(city.getB1().equals("n1_c1")){
            b1.setImageResource(R.drawable.n1_c1);
        }else if(city.getB1().equals("n1_c2")){
            b1.setImageResource(R.drawable.n1_c2);
        } else if(city.getB1().equals("n1_c3")){
            b1.setImageResource(R.drawable.n1_c3);
        } else if(city.getB1().equals("n2_c1")){
            b1.setImageResource(R.drawable.n2_c1);
        }else if(city.getB1().equals("n2_c2")){
            b1.setImageResource(R.drawable.n2_c2);
        }else if(city.getB1().equals("n3_c1")){
            b1.setImageResource(R.drawable.n3_c1);
        }else if(city.getB1().equals("n3_c2")){
            b1.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion B2

        if(city.getB2().equals("n1_c1")){
            b2.setImageResource(R.drawable.n1_c1);
        }else if(city.getB2().equals("n1_c2")){
            b2.setImageResource(R.drawable.n1_c2);
        } else if(city.getB2().equals("n1_c3")){
            b2.setImageResource(R.drawable.n1_c3);
        } else if(city.getB2().equals("n2_c1")){
            b2.setImageResource(R.drawable.n2_c1);
        }else if(city.getB2().equals("n2_c2")){
            b2.setImageResource(R.drawable.n2_c2);
        }else if(city.getB2().equals("n3_c1")){
            b2.setImageResource(R.drawable.n3_c1);
        }else if(city.getB2().equals("n3_c2")){
            b2.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion B3

        if(city.getB3().equals("n1_c1")){
            b3.setImageResource(R.drawable.n1_c1);
        }else if(city.getB3().equals("n1_c2")){
            b3.setImageResource(R.drawable.n1_c2);
        } else if(city.getB3().equals("n1_c3")){
            b3.setImageResource(R.drawable.n1_c3);
        } else if(city.getB3().equals("n2_c1")){
            b3.setImageResource(R.drawable.n2_c1);
        }else if(city.getB3().equals("n2_c2")){
            b3.setImageResource(R.drawable.n2_c2);
        }else if(city.getB3().equals("n3_c1")){
            b3.setImageResource(R.drawable.n3_c1);
        }else if(city.getB3().equals("n3_c2")){
            b3.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion C1

        if(city.getC1().equals("n1_c1")){
            c1.setImageResource(R.drawable.n1_c1);
        }else if(city.getC1().equals("n1_c2")){
            c1.setImageResource(R.drawable.n1_c2);
        } else if(city.getC1().equals("n1_c3")){
            c1.setImageResource(R.drawable.n1_c3);
        } else if(city.getC1().equals("n2_c1")){
            c1.setImageResource(R.drawable.n2_c1);
        }else if(city.getC1().equals("n2_c2")){
            c1.setImageResource(R.drawable.n2_c2);
        }else if(city.getC1().equals("n3_c1")){
            c1.setImageResource(R.drawable.n3_c1);
        }else if(city.getC1().equals("n3_c2")){
            c1.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion C2
        if(city.getC2().equals("n1_c1")){
            c2.setImageResource(R.drawable.n1_c1);
        }else if(city.getC2().equals("n1_c2")){
            c2.setImageResource(R.drawable.n1_c2);
        } else if(city.getC2().equals("n1_c3")){
            c2.setImageResource(R.drawable.n1_c3);
        } else if(city.getC2().equals("n2_c1")){
            c2.setImageResource(R.drawable.n2_c1);
        }else if(city.getC2().equals("n2_c2")){
            c2.setImageResource(R.drawable.n2_c2);
        }else if(city.getC2().equals("n3_c1")){
            c2.setImageResource(R.drawable.n3_c1);
        }else if(city.getC2().equals("n3_c2")){
            c2.setImageResource(R.drawable.n3_c2);
        }

        //Para la posicion C3
        if(city.getC3().equals("n1_c1")){
            c3.setImageResource(R.drawable.n1_c1);
        }else if(city.getC3().equals("n1_c2")){
            c3.setImageResource(R.drawable.n1_c2);
        } else if(city.getC3().equals("n1_c3")){
            c3.setImageResource(R.drawable.n1_c3);
        } else if(city.getC3().equals("n2_c1")){
            c3.setImageResource(R.drawable.n2_c1);
        }else if(city.getC3().equals("n2_c2")){
            c3.setImageResource(R.drawable.n2_c2);
        }else if(city.getC3().equals("n3_c1")){
            c3.setImageResource(R.drawable.n3_c1);
        }else if(city.getC3().equals("n3_c2")){
            c3.setImageResource(R.drawable.n3_c2);
        }


        //Para la posicion C4
        if(city.getC4().equals("n1_c1")){
            c4.setImageResource(R.drawable.n1_c1);
        }else if(city.getC4().equals("n1_c2")){
            c4.setImageResource(R.drawable.n1_c2);
        } else if(city.getC4().equals("n1_c3")){
            c4.setImageResource(R.drawable.n1_c3);
        } else if(city.getC4().equals("n2_c1")){
            c4.setImageResource(R.drawable.n2_c1);
        }else if(city.getC4().equals("n2_c2")){
            c4.setImageResource(R.drawable.n2_c2);
        }else if(city.getC4().equals("n3_c1")){
            c4.setImageResource(R.drawable.n3_c1);
        }else if(city.getC4().equals("n3_c2")){
            c4.setImageResource(R.drawable.n3_c2);
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