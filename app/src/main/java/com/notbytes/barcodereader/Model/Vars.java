package com.notbytes.barcodereader.Model;

public class Vars {
    private String Codigo_barra;
    private String Usuario;
    private String Coordenadas;
    private String Estado;
    private String estado;
    private String mensaje;
    private String Login;
    private String Password;
    private int CodigoUsuario;

    public String login() {
        return Login;
    }
    public String password() {
        return Password;
    }
    public int codigoUsuario(){ return CodigoUsuario;}
    public Vars(String login, String password) {
        Login = login;
        Password = password;
    }
}
