package com.example.city.Adaptadores;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.city.R;
import com.example.city.datos.Cityrow;

import java.util.ArrayList;

public class AdaptadorCiudad extends BaseAdapter {

    private Context context;
    private ArrayList<Cityrow> listItems;

    public AdaptadorCiudad(Context context, ArrayList<Cityrow> listItems) {
        this.context = context;
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return listItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Cityrow Item = (Cityrow) getItem(position);



        convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.fotoEdificio);
        TextView titulo = (TextView) convertView.findViewById(R.id.tituloEdificio);
        TextView precio = (TextView) convertView.findViewById(R.id.contenidoEdificio);
        TextView posicion = (TextView) convertView.findViewById(R.id.posicionEdificio);

        imgFoto.setImageResource(Item.getImgFoto());
        titulo.setText(Item.getNombre());
        precio.setText(Item.getPrecio());
        posicion.setText(Item.getPosicion());



        return convertView;
    }
}
