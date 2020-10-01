package com.notbytes.barcodereader;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.notbytes.barcodereader.Model.Transportes;
import com.notbytes.barcodereader.Model.TransportesCodigo;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HitoFacturacls extends AppCompatActivity {
    private Button GOestadoBtn;
    private Spinner spintransporte, spinmoneda, spintipopago, spindomcumento;
    private TextView despachodescrip, tipotransportedescrip, tipomonedadescrip, simbolomoneda, tipopagodescrip, documentodescrip;
    private EditText transportedescripdtx, transportedescrip, valorflete, seriulo, doculo, despachNum;
    private ListView listaTransporte;
    private ArrayAdapter<String> adaptlista;
    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hito_factura);
        GOestadoBtn = findViewById(R.id.GOestadoBtn);

        //TestApi = findViewById(R.id.TestApi);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiCyT/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);
        FillSpinner();
        Llamardatos();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.GOestadoBtn:
                FacturaHitos();
                break;
        }
    }

    public void Llamardatos(){

        //Llamar datos usuario--------------------------
        Bundle c = getIntent().getExtras();
        String receivingdata = c.getString("Key");
        String Estado = c.getString("Key2");
        String ID = c.getString("Key3");
        String DNIUsuario = c.getString("DNIUsuario");

        despachNum.setText(""+DNIUsuario);
        //String usu = receivingdata.toString();
        //TextView tv = findViewById(R.id.usuarios);
        //tv.append(receivingdata);
        //----------------------------------------------

    }

    public void FacturaHitos() {


        //String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        //final Spinner mySpinner = findViewById(R.id.estado);
        //final String Estado = mySpinner.getSelectedItem().toString();
        String valormoneda="";
        String tipotransvalue="";
        String tipopago="";
        String tipodoc="";

        String transportecodigo = transportedescripdtx.getText().toString();
        String transportedescrip2 = transportedescrip.getText().toString();
        String valorfletef = valorflete.getText().toString();
        String seriul = seriulo.getText().toString();
        String docul = doculo.getText().toString();

        if(valorfletef.matches("")){
            valorfletef="00";
        }

        if(docul.matches("")){
            Toast.makeText(getApplicationContext(), "Escriba su número de documento.", Toast.LENGTH_SHORT).show();
        }
        else{

        if (spintransporte.getSelectedItemPosition() == 0) {
             tipotransvalue = "3318";
        } else if (spintransporte.getSelectedItemPosition() == 1) {
             tipotransvalue = "3319";
        }

        if (spinmoneda.getSelectedItemPosition() == 0) {
             valormoneda = "PEN";
        } else if (spinmoneda.getSelectedItemPosition() == 1) {
             valormoneda = "USD";
        }


        if (spintipopago.getSelectedItemPosition() == 0) {
         tipopago = "3217";
            tipopagodescrip.setText("CONTADO");

        } else if (spintipopago.getSelectedItemPosition() == 1) {

            tipopago = "3218";

        } else if (spintipopago.getSelectedItemPosition() == 2) {

            tipopago = "3222";

        }

        if (spindomcumento.getSelectedItemPosition() == 0) {

            tipodoc = "0071";

        } else if (spindomcumento.getSelectedItemPosition() == 1) {

            tipodoc = "0073";

        }
                //Llamar datos usuario--------------------------
                Bundle c = getIntent().getExtras();
                String receivingdata = c.getString("Key");
                String Estado = c.getString("Key2");
                String ID = c.getString("Key3");
                String DNIUsuario = c.getString("DNIUsuario");

                String despachNumo = despachNum.getText().toString();
                String despachodni = despachodescrip.getText().toString();
                //String usu = receivingdata.toString();
                //TextView tv = findViewById(R.id.usuarios);
                //tv.append(receivingdata);
                //----------------------------------------------
                    Intent i = new Intent(HitoFacturacls.this, MainActivity.class);
                    //Intent a = new Intent(Logeo.this, Pre_estado.class);
                    Bundle b = new Bundle();
                    b.putString("Key", receivingdata);
                    b.putString("Key2", Estado);
                    b.putString("Key3", ID);
                    b.putString("Pkey1",valormoneda);
                    b.putString("Pkey2",tipotransvalue);
                    b.putString("Pkey3",tipopago);
                    b.putString("Pkey4",tipodoc);
                    b.putString("Pkey5",despachodni);
                    b.putString("Pkey6",transportecodigo);
                    b.putString("Pkey7",transportedescrip2);
                    b.putString("Pkey8",valorfletef);
                    b.putString("Pkey9", seriul);
                    b.putString("Pkey10", docul);
                    b.putString("Pkey11", despachNumo);
                    b.putString("Pkey12", DNIUsuario);

                    i.putExtras(b);
                    startActivity(i);
        }
    }
    private void FillSpinner() {
        //Spinners
        spintransporte = findViewById(R.id.spintransporte);
        spinmoneda = findViewById(R.id.spinmoneda);
        spintipopago = findViewById(R.id.spintipopago);
        spindomcumento = findViewById(R.id.spindocumento);
        //TextViews
        despachodescrip = findViewById(R.id.despachodescrip);
        transportedescrip = findViewById(R.id.transportedescrip);
        tipotransportedescrip = findViewById(R.id.tipotransportedescrip);
        tipomonedadescrip = findViewById(R.id.tipomonedadescrip);
        valorflete = findViewById(R.id.valorflete);
        simbolomoneda = findViewById(R.id.simbolomoneda);
        tipopagodescrip = findViewById(R.id.tipopagodescrip);
        documentodescrip = findViewById(R.id.documentodescrip);
        //EditTexts
        transportedescripdtx = findViewById(R.id.transportedescripdtx);
        //Lista
        listaTransporte = findViewById(R.id.listaTransporte);
        seriulo = findViewById(R.id.seriul);
        doculo = findViewById(R.id.docul);
        despachNum = findViewById(R.id.despachNum);


        //String[] wee = list2.toArray(new String[list2.size()]);
        //final String[] str={"Report 1","Report 2","Report 3","Report 4","Report 5"};
        ArrayList<String> spintransportes = new ArrayList<>();
        spintransportes.add(new String("0001"));
        spintransportes.add(new String("0002"));
        //spintransportes.add(new String("3318"));
        //spintransportes.add(new String("3319"));


        spintransporte.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        if (spintransporte.getSelectedItemPosition() == 0) {

                            tipotransportedescrip.setText("TERRESTRE");
                            String tipotransvalue = "3318";

                        } else if (spintransporte.getSelectedItemPosition() == 1) {

                            tipotransportedescrip.setText("AEREO");
                            String tipotransvalue = "3319";

                        }

                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        spinmoneda.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spno,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        if (spinmoneda.getSelectedItemPosition() == 0) {

                            tipomonedadescrip.setText("NUEVO SOL");
                            simbolomoneda.setText("S/.");

                        } else if (spinmoneda.getSelectedItemPosition() == 1) {

                            tipomonedadescrip.setText("DOLAR");
                            simbolomoneda.setText("$.");

                        }

                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        spintipopago.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        if (spintipopago.getSelectedItemPosition() == 0) {

                            tipopagodescrip.setText("CONTADO");

                        } else if (spintipopago.getSelectedItemPosition() == 1) {

                            tipopagodescrip.setText("CREDITO");

                        } else if (spintipopago.getSelectedItemPosition() == 2) {

                            tipopagodescrip.setText("DEPOSITO");

                        }

                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        spindomcumento.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        if (spindomcumento.getSelectedItemPosition() == 0) {

                            documentodescrip.setText("FACTURA");

                        } else if (spindomcumento.getSelectedItemPosition() == 1) {

                            documentodescrip.setText("BOLETA DE VENTA");

                        }

                    }

                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });

        ArrayAdapter<String> adaptertransporte =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spintransportes);
        adaptertransporte.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> spinmonedas = new ArrayList<>();
        spinmonedas.add(new String("PEN"));
        spinmonedas.add(new String("USD"));


        /////////////////////////////////////////////////////////////////////////////////////////////////////

        Transportes transportes = new Transportes("1");
        Call<List<Transportes>> call = jsonPlaceHolderApi.transportes(transportes);
        call.enqueue(new Callback<List<Transportes>>() {
            @Override
            public void onResponse(Call<List<Transportes>> call, Response<List<Transportes>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(HitoFacturacls.this, "No correcto", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Transportes> rptas = response.body();
                int tamanio = rptas.size();

                List<String> hey = new ArrayList<>();
                List<String> hola = new ArrayList<>();


                for (int i = 0 ; i<tamanio;i++){
                    Transportes rpts = rptas.get(i);
                    String descripf = rpts.descripcion();
                    String codef = rpts.codigo();

                    hey.add(descripf);
                    hola.add(codef);

                    final ArrayAdapter<String> adapterlista = new ArrayAdapter<String>(HitoFacturacls.this,
                            android.R.layout.simple_spinner_item, hey);

                    final ArrayAdapter<String> adapterlistacodef = new ArrayAdapter<String>(HitoFacturacls.this,
                            android.R.layout.simple_spinner_item, hola);

                    adapterlista.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    listaTransporte.setAdapter(adapterlista);

                    transportedescrip.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            adapterlista.getFilter().filter(s);
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                }

                listaTransporte.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String codef = listaTransporte.getItemAtPosition(position).toString();

                        TransportesCodigo transportesCodigo = new TransportesCodigo(""+codef);
                        Call<TransportesCodigo> call = jsonPlaceHolderApi.transportesCodigo(transportesCodigo);
                        call.enqueue(new Callback<TransportesCodigo>() {
                            @Override
                            public void onResponse(Call<TransportesCodigo> call, Response<TransportesCodigo> response) {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(HitoFacturacls.this, "No correcto", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                TransportesCodigo rptas = response.body();
                                String codigo = rptas.codigo();
                                transportedescripdtx.setText(codigo);

                            }

                            @Override
                            public void onFailure(Call<TransportesCodigo> call, Throwable t) {
                                Toast.makeText(HitoFacturacls.this, "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });

                        transportedescrip.setText(codef);
                        listaTransporte.setVisibility(View.GONE);
                    }
                });


            }

            @Override
            public void onFailure(Call<List<Transportes>> call, Throwable t) {
                Toast.makeText(HitoFacturacls.this, "Fallo al ingresar los datos, compruebe su red.", Toast.LENGTH_SHORT).show();
                return;
            }



        });

        transportedescrip.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    listaTransporte.setVisibility(View.VISIBLE);
                } else {
                    listaTransporte.setVisibility(View.GONE);
                }
            }
        });


        /////////////////////////////////////////////////////////////////////////////////////////////////////


        ArrayAdapter<String> adaptermoneda =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinmonedas);
        adaptermoneda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> spintipopagos = new ArrayList<>();
        spintipopagos.add(new String("0001"));
        spintipopagos.add(new String("0002"));
        spintipopagos.add(new String("0003"));
        //spintipopagos.add(new String("3217"));
        //spintipopagos.add(new String("3218"));
        //spintipopagos.add(new String("3222"));

        ArrayAdapter<String> adaptertipopago =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spintipopagos);
        adaptertipopago.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<String> spindomcumentos = new ArrayList<>();
        spindomcumentos.add(new String("0001"));
        spindomcumentos.add(new String("0002"));
        //spindomcumentos.add(new String("0071"));
        //spindomcumentos.add(new String("0073"));


        ArrayAdapter<String> adapterdocumento =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spindomcumentos);
        adapterdocumento.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // Spinner spinYear = (Spinner)findViewById(R.id.spin);
        spintransporte.setAdapter(adaptertransporte);
        spindomcumento.setAdapter(adapterdocumento);
        spinmoneda.setAdapter(adaptermoneda);
        spintipopago.setAdapter(adaptertipopago);


    }

    public void Llenarlista() {



    }
}
