package com.example.wrotter.clases.vo;

public class PreguntaVo {
    private  int id;
    private String pregunta;
    private String opA;
    private String opB;
    private String opC;
    private String opD;
    private String opCorrecta;

    public PreguntaVo(int id, String pregunta, String opA, String opB, String opC, String opD, String opCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.opCorrecta = opCorrecta;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpA() {
        return opA;
    }

    public void setOpA(String opA) {
        this.opA = opA;
    }

    public String getOpB() {
        return opB;
    }

    public void setOpB(String opB) {
        this.opB = opB;
    }

    public String getOpC() {
        return opC;
    }

    public void setOpC(String opC) {
        this.opC = opC;
    }

    public String getOpD() {
        return opD;
    }

    public void setOpD(String opD) {
        this.opD = opD;
    }

    public String getOpCorrecta() {
        return opCorrecta;
    }

    public void setOpCorrecta(String opCorrecta) {
        this.opCorrecta = opCorrecta;
    }
}
