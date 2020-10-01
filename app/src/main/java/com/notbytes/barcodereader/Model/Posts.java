package com.notbytes.barcodereader.Model;

public class Posts {
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

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }
    public Posts(String codigo_barra, String usuario, String latitud, String longitud, String estado, String Ganio, String Gnro, String Gguia) {
        Codigo_barra = codigo_barra;
        Usuario = usuario;
        Latitud = latitud;
        Longitud = longitud;
        Estado = estado;
        gAnio = Ganio;
        gNro = Gnro;
        gGuia = Gguia;
    }
}
