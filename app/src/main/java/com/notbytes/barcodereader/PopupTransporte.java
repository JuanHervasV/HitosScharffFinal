package com.notbytes.barcodereader;

import androidx.annotation.Nullable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.notbytes.barcodereader.io.APIRetrofitInterface;


public class PopupTransporte extends Activity {
    private APIRetrofitInterface jsonPlaceHolderApi;
    Button aplicar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_transporte);

        DisplayMetrics dm= new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(950, 610);

        onTouch();
    }

    public void onTouch() {
        aplicar = findViewById(R.id.aplicarbtn);

        aplicar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                    v.setBackgroundResource(R.drawable.rounded_cornerneutral);
                    //v.setBackgroundColor(Color.parseColor("#9C9C9C"));
                }

                if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setBackgroundResource(R.drawable.rounded_cornersscharff);
                    //v.setBackgroundColor(Color.parseColor("#FF7177"));
                }
                return false;
            }
        });


    }

    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.aplicarbtn:
                //startActivity(new Intent(this, DespacharFinal.class));
                AplicarDatos();
                break;
        }

    }

    public void AplicarDatos(){

    String codef = "abc";
    String descripf = "def";

    GlobalClass vrl = new GlobalClass();
    String TransporteValue = vrl.getTransporteValue();

        vrl.setTransporteValue(codef);
        Intent i = new Intent(PopupTransporte.this, HitoFacturacls.class);
        startActivity(i);
        finish();

    }

}