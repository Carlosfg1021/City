package com.example.city.Modelos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.city.AcercadeActivity;
import com.example.city.Fragment.CiudadFragment;
import com.example.city.Fragment.EstudioFragment;
import com.example.city.Fragment.PerfilFragment;
import com.example.city.Fragment.UsuarioFragment;
import com.example.city.Inicio.InicioSesion;
import com.example.city.Inicio.RegistroActivity;
import com.example.city.PerfilUsuarioActivity;
import com.example.city.PortadaActivity;
import com.example.city.R;
import com.example.city.datos.Ciudad;
import com.example.city.datos.Usuario;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView menuNavegacion;
    com.google.android.material.navigation.NavigationView menuLateral;
    private androidx.drawerlayout.widget.DrawerLayout drawerLayout;
    public static String uidUsuario;
    public static String uidCiudad;

    public static Usuario user = new Usuario();
    public static Ciudad city = new Ciudad();
    boolean isRegistrado =false;

    TextView txtNombre, txtCorreo;
    ImageView imgUsuario;

    FirebaseUser userDB = FirebaseAuth.getInstance().getCurrentUser(); // es publico

    //Variables para agregar a firebase
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;


    //Variables para consultar al usuario
    private List<Usuario> listUsuario = new ArrayList<Usuario>();
    ArrayAdapter<Usuario> arrayAdapterUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*TextView menuNombre = (TextView) menuNavegacion.findViewById(R.id.menuNombreUsuario);
        TextView menuCorreo = (TextView) menuNavegacion.findViewById(R.id.menuCorreoUsuario); este es una forma diferente para llamada
        el layout aunque solo con texview tambien se puede hacer `pero no funciona paque
        */


        inicializarFirebase();

        //intent.putExtra("idcardview",String.valueOf(getRef(position).getKey()));

        /*if(user!=null){

            String nombre = user.getNombre();
            String correo = user.getCorreo();

            menuNombre.setText(nombre);
            menuCorreo.setText(correo);

            Kevin aqui esta el codigo comentado lo saque del metodo
        }*/

        Toast.makeText(this,userDB.getDisplayName(),Toast.LENGTH_SHORT).show();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (signInAccount != null){

            uidUsuario = (signInAccount.getId());





        }

        consultarExisteUsuario(); //Este metodo nos sirve para ver si nuestro usuario es primera vez que usa la app
        consultarCiudad();



        //Toast.makeText(this,uidUsuario,Toast.LENGTH_SHORT).show();


        //consultarCiudad();







        menuNavegacion = (BottomNavigationView) findViewById(R.id.menu_navegacion);
        menuNavegacion.setSelectedItemId(R.id.btn_nav_estudio);

        setToolBar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        Fragment fragment = new EstudioFragment();

        menuNavegacion.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.btn_nav_usuarios:
                        fragment = new UsuarioFragment();
                        break;
                    case R.id.btn_nav_estudio:
                        fragment = new EstudioFragment();
                        break;
                    case R.id.btn_nav_ciudades:
                        fragment = new CiudadFragment();
                        break;
                    case R.id.btn_nav_perfil:
                        fragment = new PerfilFragment();
                        break;

                }

                return cargarFragmento(fragment);
            }
        });


        menuLateral = findViewById(R.id.navigationId);//Obtenemos el objeto del xml
        View headerView = menuLateral.getHeaderView(0);
        txtNombre = headerView.findViewById(R.id.menuNombreUsuario);
        txtCorreo = headerView.findViewById(R.id.menuCorreoUsuario);
        imgUsuario = headerView.findViewById(R.id.menuImagenPerfil);

            cargarDatosMenu();



        menuLateral.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;

                switch (item.getItemId()){
                    case R.id.btn_nav_alerta_usuarios_iz:
                        fragment = new UsuarioFragment();
                        menuNavegacion.setSelectedItemId(R.id.btn_nav_usuarios);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.btn_nav_alerta_estudio_iz:
                        fragment = new EstudioFragment();
                        menuNavegacion.setSelectedItemId(R.id.btn_nav_estudio);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.btn_nav_mi_ciudad_iz:
                        fragment = new CiudadFragment();
                        menuNavegacion.setSelectedItemId(R.id.btn_nav_ciudades);
                        drawerLayout.closeDrawers();
                        break;
                    case R.id.btn_nav_pefil_iz:
                        fragment = new PerfilFragment();
                        menuNavegacion.setSelectedItemId(R.id.btn_nav_perfil);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.btn_acercade:
                        Intent intent = new Intent(MainActivity.this, AcercadeActivity.class);
                        startActivity(intent);
                        break;


                    case R.id.btn_cerrar:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent1 = new Intent(getApplicationContext(), InicioSesion.class);
                        startActivity(intent1);
                        break;

                }
                return cargarFragmento(fragment);
            }
        });


    }





    @Override
    public void onResume() {
        super.onResume();





    }

    private void goRegistrar() {

        Intent intent = new Intent(this, RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);


    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }




    private void cargarDatosMenu(){
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);

                    if (u.getUid().equals(uidUsuario)){

                    txtNombre.setText(u.getNombre());
                    txtCorreo.setText(u.getCorreo());

                        try {
                            Glide.with(getApplicationContext())
                                    .load(u.getFotoUrl())
                                    .into((ImageView) imgUsuario);
                        } catch (Exception e) {

                        }



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


    private void consultarExisteUsuario(){
        databaseReference.child("Usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listUsuario.clear();

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                    Usuario u = objSnapshot.getValue(Usuario.class);


                    if (u.getUid().equals(uidUsuario)){
                        isRegistrado = true;
                        uidCiudad = u.getIdCiudad();
                       // Toast.makeText(MainActivity.this, "SI ESTA REGISTRADO", Toast.LENGTH_SHORT).show();

                        user.setUid(u.getNickname());
                        user.setCorreo(u.getCorreo());
                        user.setNickname(u.getNickname());
                        user.setNombre(u.getNombre());
                        user.setInstitucion(u.getInstitucion());
                        //CARGAR CIUDAD
                        user.setPuntosGanar(u.getPuntosGanar());
                        user.setPuntosPerder(u.getPuntosPerder());
                        //CARGAR EXPERIENCIA

                        user.setMonedas(u.getMonedas());


                        break;

                    }
                    else{

                    }

                }

                if(isRegistrado==false){
                   // Toast.makeText(MainActivity.this, "NOOO esta registrado", Toast.LENGTH_SHORT).show();
                    goRegistrar();
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


    private void consultarCiudad(){

        databaseReference.child("Ciudad").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                   city = objSnapshot.getValue(Ciudad.class);

                    if (city.getUid().equals(uidCiudad)){
                        //cargar nombre
                        city.setNombre(city.getNombre());
                        //CARGAR EXPERIENCIA
                        city.setXp(city.getXp());
                        city.setUid(uidCiudad);
                        city.setIdUsuario(uidUsuario);

                        city.setA1(city.getA1());
                        city.setA2(city.getA2());
                        city.setA3(city.getA3());
                        city.setA4(city.getA4());
                        city.setA5(city.getA5());

                        city.setB1(city.getB1());
                        city.setB2(city.getB2());
                        city.setB3(city.getB3());
                        city.setB4(city.getB4());
                        city.setB5(city.getB5());

                        city.setC1(city.getC1());
                        city.setC2(city.getC2());
                        city.setC3(city.getC3());
                        city.setC4(city.getC4());
                        city.setC5(city.getC5());


                        break;

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public boolean cargarFragmento(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framContenedor, fragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit();
            return true;
        }

        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case R.id.btn_nav_usuarios:
                fragment = new UsuarioFragment();
                break;
            case R.id.btn_nav_estudio:
                fragment = new EstudioFragment();
                break;
            case R.id.btn_nav_ciudades:
                fragment = new CiudadFragment();
                break;
            case R.id.btn_nav_perfil:
                fragment = new PerfilFragment();
                break;

        }

        return cargarFragmento(fragment);
    }

    private void setToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}