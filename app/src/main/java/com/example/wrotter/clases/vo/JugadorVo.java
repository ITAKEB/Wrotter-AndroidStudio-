package com.example.wrotter.clases.vo;

public class JugadorVo implements Comparable<JugadorVo>{

    private int id;
    private String nombre;
    private String contraseña;
    private int avatar;
    private int puntaje;

    public JugadorVo(){

    }

    public JugadorVo(int id, String nombre, String contraseña, int avatar, int puntaje) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.avatar = avatar;
        this.puntaje = puntaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public int compareTo(JugadorVo o) {
        return this.puntaje-o.puntaje;
    }
}
