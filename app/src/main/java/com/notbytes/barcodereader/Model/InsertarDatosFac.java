package com.notbytes.barcodereader.Model;

public class InsertarDatosFac {

    private String Codigo_barra;
    private String Usuario;
    private String Coordenadas;
    private String Estado;
    private String estado;
    private String mensaje;
    private String Latitud;
    private String Longitud;
    private String gAnio;
    private String gNro;
    private String gGuia;
    private String facturadoc;

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }
    public InsertarDatosFac(String codigo_barra, String usuario, String latitud, String longitud, String estado, String Ganio, String Gnro, String Gguia, String Facturadoc) {
        Codigo_barra = codigo_barra;
        Usuario = usuario;
        Latitud = latitud;
        Longitud = longitud;
        Estado = estado;
        gAnio = Ganio;
        gNro = Gnro;
        gGuia = Gguia;
        facturadoc = Facturadoc;
    }
}
