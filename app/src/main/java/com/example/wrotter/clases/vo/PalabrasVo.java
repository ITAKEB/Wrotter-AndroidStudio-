package com.example.wrotter.clases.vo;

public class PalabrasVo {
    private int id;
    private String palabra;
    private String definicion;


    public PalabrasVo(int id, String palabra, String definicion) {
        this.id = id;
        this.palabra = palabra;
        this.definicion = definicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getDefinicion() {
        return definicion;
    }

    public void setDefinicion(String definicion) {
        this.definicion = definicion;
    }

}
