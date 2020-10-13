package com.example.city.Modelos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.city.Fragment.CiudadFragment;
import com.example.city.Fragment.EstudioFragment;
import com.example.city.Fragment.PerfilFragment;
import com.example.city.Fragment.UsuarioFragment;
import com.example.city.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, com.google.android.material.navigation.NavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView menuNavegacion;
    com.google.android.material.navigation.NavigationView menuLateral;
    private androidx.drawerlayout.widget.DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuNavegacion = (BottomNavigationView) findViewById(R.id.menu_navegacion);
        menuNavegacion.setSelectedItemId(R.id.btn_nav_estudio);

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