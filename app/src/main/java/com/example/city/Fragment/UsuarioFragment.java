package com.example.city.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.city.Adaptadores.AdaptadorUsuarios;
import com.example.city.Entidades.Usuarios;
import com.example.city.R;

import java.util.ArrayList;


public class UsuarioFragment extends Fragment {

    AdaptadorUsuarios adaptadorUsuarios;
    RecyclerView recyclerViewUsuarios;
    ArrayList<Usuarios> listausuarios;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuario, container, false);

        recyclerViewUsuarios = view.findViewById(R.id.recycleview_usuarios);
        listausuarios = new ArrayList<>();

        cargarlista();
        mostraData();


        return view;

    }



    public void cargarlista(){

        listausuarios.add(new Usuarios("Carlosfg1021","Gualan City"));
        listausuarios.add(new Usuarios("Ronedy30","Zacapa City"));
    }

    public void mostraData(){

        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(getContext()));
        adaptadorUsuarios = new AdaptadorUsuarios(getContext(),listausuarios);
        recyclerViewUsuarios.setAdapter(adaptadorUsuarios);

    }

    public void juego(){}


}