package com.notbytes.barcodereader;

import android.app.Activity;

public class GlobalClass extends Activity {

    private String TransporteValue;

    public String getTransporteValue(){
        return TransporteValue;
    }

    public void setTransporteValue(String stv){
        TransporteValue = stv;
    }


}
