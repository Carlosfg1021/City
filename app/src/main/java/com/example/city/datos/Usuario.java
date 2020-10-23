package com.example.city.datos;

public class Usuario {

    public Usuario(){

    }

    private String uid;
    private String nickname;
    private String nombre;
    private String correo;
    private String institucion;
    private String ciudad;
    private String idCiudad;
    private int puntosGanar;
    private int puntosPerder;
    private String fotoUrl;
    private int monedas;


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(String idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getPuntosGanar() {
        return puntosGanar;
    }

    public void setPuntosGanar(int puntosGanar) {
        this.puntosGanar = puntosGanar;
    }

    public int getPuntosPerder() {
        return puntosPerder;
    }

    public void setPuntosPerder(int puntosPerder) {
        this.puntosPerder = puntosPerder;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }
}
