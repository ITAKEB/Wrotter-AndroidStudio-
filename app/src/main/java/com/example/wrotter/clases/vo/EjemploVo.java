package com.example.wrotter.clases.vo;

public class EjemploVo {
    int id;
    String ejemplo1;
    String ejemplo2;

    public EjemploVo() {

    }

    public EjemploVo(int id, String ejemplo1, String ejemplo2) {
        this.id = id;
        this.ejemplo1 = ejemplo1;
        this.ejemplo2 = ejemplo2;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEjemplo1() {
        return ejemplo1;
    }

    public void setEjemplo1(String ejemplo1) {
        this.ejemplo1 = ejemplo1;
    }

    public String getEjemplo2() {
        return ejemplo2;
    }

    public void setEjemplo2(String ejemplo2) {
        this.ejemplo2 = ejemplo2;
    }
}
