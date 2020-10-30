package com.example.city.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.Entidades.Usuarios;
import com.example.city.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorUsuarios extends FirebaseRecyclerAdapter<Usuarios,AdaptadorUsuarios.AdaptadorUs> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdaptadorUsuarios(@NonNull FirebaseRecyclerOptions<Usuarios> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final AdaptadorUs holder, int position, @NonNull Usuarios usuarios) {

        holder.txtnickname.setText(usuarios.getNickname());
        holder.txtciudad.setText(usuarios.getInstitucion());
        holder.txtid.setText(usuarios.getUid());

        /*holder.itemView.setOnClickListener(new View.OnClickListener() { tambien aqui se puede poner amigo
            @Override
            public void onClick(View view) {

            }
        });*/

    }

    @NonNull
    @Override
    public AdaptadorUs onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_usuarios_ciudad,parent,false);
        return new AdaptadorUs(view);
    }

    // public  TextView txtnickname,txtciudad;

   public class AdaptadorUs extends RecyclerView.ViewHolder{

       TextView txtnickname,txtciudad,txtid;

       public AdaptadorUs(@NonNull View itemView) {
           super(itemView);
           txtnickname = itemView.findViewById(R.id.txtnickname);
           txtciudad = itemView.findViewById(R.id.txtciudad);
           txtid = itemView.findViewById(R.id.idcard);

           txtid.setVisibility(View.INVISIBLE);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           });

       }
   }
}
