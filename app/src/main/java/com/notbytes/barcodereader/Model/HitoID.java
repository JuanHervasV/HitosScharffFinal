package com.notbytes.barcodereader.Model;

public class HitoID {
    private String hito;
    private String ID;
    private String CodigoEstado;
    private String Descripcion;

    public HitoID(String Hito) {
        hito = Hito;
    }

    public String ID(){return ID;}
    public String codigoEstado() {
        return CodigoEstado;
    }
    public String descripcion() { return Descripcion; }

}
