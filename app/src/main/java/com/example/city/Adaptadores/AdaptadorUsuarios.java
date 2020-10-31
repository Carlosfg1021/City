package com.example.city.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.city.ConstruirActivity;
import com.example.city.Entidades.Usuarios;
import com.example.city.Modelos.MainActivity;
import com.example.city.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorUsuarios extends RecyclerView.ViewHolder {

    public TextView txtnickname;
    public TextView txtciudad;
    public TextView idcard;
    public TextView idCiudadCard;
    public View v;

    public AdaptadorUsuarios(@NonNull View itemView) {
        super(itemView);

        txtnickname = itemView.findViewById(R.id.txtnickname);
        txtciudad = itemView.findViewById(R.id.txtciudad);
        idcard = itemView.findViewById(R.id.idcard);
        idCiudadCard = itemView.findViewById(R.id.idCiudadCard);
        idcard.setVisibility(View.INVISIBLE);
        v=itemView;
    }
}
