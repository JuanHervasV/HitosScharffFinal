package com.notbytes.barcodereader.Model;

public class SucursalHitos {

    private String codigo_barra;
    private String ID;
    private String CodigoEstado;
    private String Sucursal;
    private String Fecha;

    public SucursalHitos(String Codigo_barra) {
        codigo_barra = Codigo_barra;
    }

    public String sucursal() { return Sucursal; }
    public String fecha(){return Fecha;}
}
