package com.notbytes.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.notbytes.barcodereader.Model.Vars;
import com.notbytes.barcodereader.io.APIRetrofitInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Logeo extends AppCompatActivity {

    private APIRetrofitInterface jsonPlaceHolderApi;
    //private TextView TestApi;
    private TextView LoginText;
    private TextView PasswordText;
    private String Usuario;
    private String Pass;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        LoginText = findViewById(R.id.editText_login_username);
        PasswordText = findViewById(R.id.editText_login_password);

        //TestApi = findViewById(R.id.TestApi);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://200.37.50.53/ApiCyT/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
       jsonPlaceHolderApi = retrofit.create(APIRetrofitInterface.class);

        this.Usuario = LoginText.getText().toString();
        this.Pass = PasswordText.getText().toString();
    }

    public void onClick(View v) {

        if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        switch (v.getId()) {
            case R.id.button_login_login:
                createPost();
                break;

        }

    }
    private void createPost(){

        String Usua = LoginText.getText().toString();
        String Pas = PasswordText.getText().toString();

        Vars vars = new Vars(Usua, Pas);

        Call<Vars> call = jsonPlaceHolderApi.createPost(vars);
        call.enqueue(new Callback<Vars>() {
            @Override
            public void onResponse(Call<Vars> call, Response<Vars> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(Logeo.this, "Usuario/Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    return;
                }
                Vars postsResponse = response.body();
                String Login = postsResponse.login();
                String Password = postsResponse.password();
                Integer CodigoUsuario = postsResponse.codigoUsuario();


                Toast.makeText(Logeo.this, "Autentificación correcta", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Logeo.this, Pre_estado.class);
                //Intent a = new Intent(Logeo.this, Pre_estado.class);

                Bundle c = new Bundle();

                c.putString("DNIUsuario", Login);
                c.putInt("CodigoUsuario", CodigoUsuario);
                i.putExtras(c);
                startActivity(i);
                //RecuperarUsuario();

            }
            @Override
            public void onFailure(Call<Vars> call, Throwable t) {
                Toast.makeText(Logeo.this, "Revise su conexión a internet.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RecuperarUsuario(){
        String passingdata = LoginText.getText().toString();
        //Intent i = new Intent(Logeo.this, MainActivity.class);
        String usuario = LoginText.getText().toString();

        Intent i = new Intent(Logeo.this, Pre_estado.class);
        //Intent a = new Intent(Logeo.this, Pre_estado.class);
        Bundle c = new Bundle();
        c.putString("Key", passingdata);
        i.putExtras(c);
        startActivity(i);
    }

    public String getUsuario() {
        return this.Usuario;
    }

    public String getPassword() {
        return this.Pass;
    }
}