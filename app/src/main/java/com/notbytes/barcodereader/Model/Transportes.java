package com.notbytes.barcodereader.Model;

public class Transportes {


    private String Codigo;
    private String Descripcion;
    private String nvm;


    public Transportes(String Nvm) {
        nvm = Nvm;
    }

    public String codigo() {
        return Codigo;
    }
    public String descripcion() { return Descripcion; }
}
