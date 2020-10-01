package com.notbytes.barcodereader.Model;

public class Hitos {

    private String CodigoHC;
    private String CodigoEstado;
    private String Descripcion;
    private String Login;
    private String Password;
    private int usuario;
    private String ID;


    public Hitos(int Usuario) {
        usuario = Usuario;
    }

    public String ID(){return ID;}
    public String codigoEstado() {
        return CodigoEstado;
    }
    public String descripcion() { return Descripcion; }
    public String codigoHC(){ return CodigoHC; }

}
