package com.example.city.Entidades;

public class Usuarios {

    private String nickname;
    private String nombre_ciudad;

    public Usuarios(){}

    public Usuarios(String nickname, String nombre_ciudad) {
        this.nickname = nickname;
        this.nombre_ciudad = nombre_ciudad;
    }

    public String getNickname() {
        return nickname;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }




}
