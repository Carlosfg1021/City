package com.example.city.datos;

import android.widget.ImageView;

public class Cityrow {

    public Cityrow(String id, String nombre, String precio, int imgFoto) {
        this.id=id;
        this.nombre=nombre;
        this.precio=precio;
        this.imgFoto=imgFoto;
    }
    private String id;
    private String nombre;
    private String precio;
    private int imgFoto; //se representa con id de foto


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }


    public int getImgFoto() {
        return imgFoto;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }


}
