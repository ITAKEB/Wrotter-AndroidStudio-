package com.example.wrotter.clases.vo;

public class AprendidasVo {
    private int id;
    private int id_palabra;
    private int id_jugador;

    public AprendidasVo(){

    }

    public AprendidasVo(int id, int id_palabra, int id_jugador) {
        this.id = id;
        this.id_palabra = id_palabra;
        this.id_jugador = id_jugador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_palabra() {
        return id_palabra;
    }

    public void setId_palabra(int id_palabra) {
        this.id_palabra = id_palabra;
    }

    public int getId_jugador() {
        return id_jugador;
    }

    public void setId_jugador(int id_jugador) {
        this.id_jugador = id_jugador;
    }
}
