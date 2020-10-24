package com.example.city.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.city.Entidades.Usuarios;
import com.example.city.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.ViewHolder> implements  View.OnClickListener {


    LayoutInflater inflater;
    ArrayList<Usuarios>model;
    private View.OnClickListener listener;


    public AdaptadorUsuarios(Context context, ArrayList<Usuarios> model){


        this.inflater = LayoutInflater.from(context);
        this.model = model;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cardview_usuarios_ciudad,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }


    public void setOnClickListener(View.OnClickListener listener){

        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


      String nickname = model.get(position).getNickname();
      String ciudad = model.get(position).getNombre_ciudad();

      holder.nickname.setText(nickname);
      holder.ciudad.setText(ciudad);

    }

    @Override
    public int getItemCount() {
        return model.size();
    }


    @Override
    public void onClick(View view) {

        if(listener!=null){

            listener.onClick(view);

        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

       TextView nickname, ciudad;
        public ViewHolder(@NonNull View itemView){
            super(itemView);


            nickname = itemView.findViewById(R.id.txtnickname);
            ciudad = itemView.findViewById(R.id.txtciudad);

        }


    }





}
