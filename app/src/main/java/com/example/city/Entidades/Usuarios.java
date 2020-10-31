package com.example.city.Entidades;

public class Usuarios {


    String nickname;
    String institucion;

    private String uidCiudad;



    String uid;

    public Usuarios(){}

    public Usuarios(String nickname, String institucion,String uid, String uidCiudad) {
        this.nickname = nickname;
        this.institucion = institucion;
        this.uid= uid;
        this.uidCiudad = uidCiudad;
    }




    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getUidCiudad() {
        return uidCiudad;
    }

    public void setUidCiudad(String uidCiudad) {
        this.uidCiudad = uidCiudad;
    }
}
