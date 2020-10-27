package com.example.city.Fragment;

import android.content.Context;
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
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class UsuarioFragment extends Fragment {

    private RecyclerView recyclerView;
    AdaptadorUsuarios adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuario, container, false);
        recyclerView = view.findViewById(R.id.recycleview_usuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Usuarios> options =
                new FirebaseRecyclerOptions.Builder<Usuarios>()
                     .setQuery(FirebaseDatabase.getInstance().getReference().child("Usuario"),Usuarios.class)
                     .build();


        adapter = new AdaptadorUsuarios(options);
        recyclerView.setAdapter(adapter);


        return view;

    }


    @Override
    public void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }










}