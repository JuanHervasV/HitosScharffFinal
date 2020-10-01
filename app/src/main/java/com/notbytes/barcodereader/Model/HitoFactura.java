package com.notbytes.barcodereader.Model;

public class HitoFactura {

    private String usuario;
    private String despcode;
    private String tipotrans;
    private String flete_mon;
    private String flete;
    private String mft_tipopago;
    private String tipodoc;
    private String seriedoc;
    private String fact_flete;
    private String transcode;

    private String estado;
    private String mensaje;

    public HitoFactura(String Usuario, String Despcode, String Transcode, String Tipotrans, String Flete_mon, String Flete, String Mft_tipopago, String Tipodoc, String Seriedoc,String Fact_flete ) {

        usuario = Usuario;
        despcode = Despcode;
        transcode = Transcode;
        tipotrans = Tipotrans;
        flete_mon = Flete_mon;
        flete = Flete;
        mft_tipopago = Mft_tipopago;
        tipodoc = Tipodoc;
        seriedoc = Seriedoc;
        fact_flete = Fact_flete;

    }

    public String mensaje() {
        return mensaje;
    }
    public String estado() {
        return estado;
    }

}
