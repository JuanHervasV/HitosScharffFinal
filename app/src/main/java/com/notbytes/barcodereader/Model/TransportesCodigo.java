package com.notbytes.barcodereader.Model;

public class TransportesCodigo {

    private String Codigo;
    private String Descripcion;
    private String datos;


    public TransportesCodigo(String Datos) {
        datos = Datos;
    }

    public String codigo() {
        return Codigo;
    }
    public String descripcion() { return Descripcion; }

}
