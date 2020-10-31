package com.example.city.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.city.Adaptadores.AdaptadorUsuarios;
import com.example.city.Entidades.Usuarios;
import com.example.city.PerfilUsuarioActivity;
import com.example.city.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class UsuarioFragment extends Fragment {

     RecyclerView recyclerView;
     FirebaseRecyclerOptions<Usuarios> options;
     FirebaseRecyclerAdapter<Usuarios, AdaptadorUsuarios> adapter;
     DatabaseReference DataRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_usuario, container, false);
        DataRef = FirebaseDatabase.getInstance().getReference().child("Usuario");
        recyclerView = view.findViewById(R.id.recycleview_usuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        loadData();

        return view;


    }


    private  void  loadData(){

       options = new FirebaseRecyclerOptions.Builder<Usuarios>().setQuery(DataRef,Usuarios.class).build();
       adapter = new FirebaseRecyclerAdapter<Usuarios, AdaptadorUsuarios>(options) {
           @Override
           protected void onBindViewHolder(@NonNull final AdaptadorUsuarios holder, final int position, @NonNull final Usuarios usuarios) {

               holder.txtnickname.setText(usuarios.getNickname());
               holder.txtciudad.setText(usuarios.getInstitucion());
               holder.idcard.setText(usuarios.getUid());
               holder.v.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       /*Intent intent = new Intent(getActivity().getApplicationContext(),PerfilUsuarioActivity.class);
                       intent.putExtra("nombre",getRef(position).getKey());
                       startActivity(intent);*/
                       Toast.makeText(getActivity(),usuarios.getUid(),Toast.LENGTH_LONG).show();
                   }
               });

           }

           @NonNull
           @Override
           public AdaptadorUsuarios onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

               View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usuarios_ciudad,parent,false);
               return new AdaptadorUsuarios(v);
           }
       };

       adapter.startListening();
       recyclerView.setAdapter(adapter);

    }

}