package com.example.city.Entidades;

public class Usuarios {


    String nickname;
    String institucion;

    public Usuarios(){}

    public Usuarios(String nickname, String institucion) {
        this.nickname = nickname;
        this.institucion = institucion;
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


}
