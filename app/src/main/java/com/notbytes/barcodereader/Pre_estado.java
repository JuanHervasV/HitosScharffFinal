package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.HitoID;
import com.notbytes.barcodereader.Model.Hitos;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Pre_estado extends AppCompatActivity {
    private Button Btnsig, BtnFake;
    private Spinner spin;
    private TextView txtintro;
    private String IDf;

    private APIRetrofitInterface jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Btnsig = findViewById(R.id.btnsig);
        spin = findViewById(R.id.estado);
        txtintro = findViewById(R.id.preestado);
        setContentView(R.layout.activity_pre_estado);

        //TestApi = findViewById(R.id.TestApi);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiCyT/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        //FillSpinner();

        createPost();
        //RecuperarUsuario();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnsig:
                RecuperarUsuario();
                break;

        }
    }

    private void FillSpinner() {
        spin = findViewById(R.id.estado);
        //String[] wee = list2.toArray(new String[list2.size()]);
        //final String[] str={"Report 1","Report 2","Report 3","Report 4","Report 5"};
        ArrayList<String> str = new ArrayList<>();
        str.add(new String("Recibido en MIAMI"));
        str.add(new String("En tránsito"));
        str.add(new String("En almacén Scharff(LIMA)"));
        str.add(new String("Entregado"));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, str);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Spinner spinYear = (Spinner)findViewById(R.id.spin);
        spin.setAdapter(adapter);
    }

    public void RecuperarSpinner() {
        Spinner mySpinner = findViewById(R.id.estado);
        final String Estado = mySpinner.getSelectedItem().toString();

        //Recuperar datos-------------------------------
        Intent i = new Intent(Pre_estado.this, MainActivity.class);
        //Intent a = new Intent(Logeo.this, Pre_estado.class);
        Bundle b = new Bundle();
        b.putString("Key", Estado);
        i.putExtras(b);
        startActivity(i);
        //-----------------------------------------------

    }

    private void createPost() {
        spin = findViewById(R.id.estado);
        //Llamar datos ---------------------------------------------------------
        Bundle b = getIntent().getExtras();
        final String DNIUsuario = b.getString("DNIUsuario");
        final String Password = b.getString("Password");
        final Integer CodigoUsuario = b.getInt("CodigoUsuario");
        //----------------------------------------------------------------------
        Hitos hitos = new Hitos(CodigoUsuario);

        Call<List<Hitos>> call = jsonPlaceHolderApi.createPost(hitos);
        call.enqueue(new Callback<List<Hitos>>() {
            @Override
            public void onResponse(Call<List<Hitos>> call, Response<List<Hitos>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Pre_estado.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Hitos> postsResponse = response.body();
                //List<Hitos> data = new ArrayList<>();
                //List<Hitos> timeList = new Gson().fromJson(json, new TypeToken<List<Hitos>>(){}.getTypeToken());

                List<String> hey = new ArrayList<>();

                for (int i = 0; i < postsResponse.size(); i++) {
                    String d = postsResponse.get(i).descripcion();
                    //IDf = postsResponse.get(i).ID();

                    hey.add(d);

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(Pre_estado.this,
                            android.R.layout.simple_spinner_item, hey);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin.setAdapter(adapter);

                    //System.out.println(data.get(i)); //prints element i
                    //Toast.makeText(Pre_estado.this, "Autentificación correcta"+d, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<List<Hitos>> call, Throwable t) {
                Toast.makeText(Pre_estado.this, "Revise su conexión a internet.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RecuperarUsuario() {
        //String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        final Spinner mySpinner = findViewById(R.id.estado);
        final String Estado = mySpinner.getSelectedItem().toString();


        HitoID hitoid = new HitoID(Estado);

        Call<HitoID> call = jsonPlaceHolderApi.createPost(hitoid);
        call.enqueue(new Callback<HitoID>() {
            @Override
            public void onResponse(Call<HitoID> call, Response<HitoID> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Pre_estado.this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
                    return;
                }

                HitoID postsResponse = response.body();
                String ID = postsResponse.ID();

                //Llamar datos usuario--------------------------
                Bundle c = getIntent().getExtras();
                String receivingdata = c.getString("Key");
                String DNIUsuario = c.getString("DNIUsuario");

                //String usu = receivingdata.toString();
                //TextView tv = findViewById(R.id.usuarios);
                //tv.append(receivingdata);
                //----------------------------------------------

                if (mySpinner.getSelectedItemPosition() == 0){

                    Intent i = new Intent(Pre_estado.this, HitoFacturacls.class);
                    //Intent a = new Intent(Logeo.this, Pre_estado.class);
                    Bundle b = new Bundle();
                    b.putString("Key", receivingdata);
                    b.putString("Key2", Estado);
                    b.putString("Key3", ID);
                    b.putString("DNIUsuario", DNIUsuario);
                    i.putExtras(b);
                    startActivity(i);

                }
                else if (mySpinner.getSelectedItemPosition() == 1){

                    //Intent i = new Intent(Logeo.this, MainActivity.class);
                    Intent i = new Intent(Pre_estado.this, MainActivity.class);
                    //Intent a = new Intent(Logeo.this, Pre_estado.class);
                    Bundle b = new Bundle();
                    b.putString("Key", receivingdata);
                    b.putString("Key2", Estado);
                    b.putString("Key3", ID);
                    i.putExtras(b);
                    startActivity(i);

                }

            }

            @Override
            public void onFailure(Call<HitoID> call, Throwable t) {
                Toast.makeText(Pre_estado.this, "Revise su conexión a internet.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}