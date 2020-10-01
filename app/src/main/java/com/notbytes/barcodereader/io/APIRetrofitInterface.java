package com.notbytes.barcodereader.io;

import com.notbytes.barcodereader.Model.HitoFactura;
import com.notbytes.barcodereader.Model.Hitos;
import com.notbytes.barcodereader.Model.HitoID;
import com.notbytes.barcodereader.Model.InsertarDatosFac;
import com.notbytes.barcodereader.Model.SucursalHitos;
import com.notbytes.barcodereader.Model.Transportes;
import com.notbytes.barcodereader.Model.TransportesCodigo;
import com.notbytes.barcodereader.Model.Vars;
import com.notbytes.barcodereader.Model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRetrofitInterface {

    @GET("posts")
    Call<List<Posts>> getPosts();

    @POST("Hitos")
    Call<List<Hitos>> createPost(@Body Hitos hitos);

    @POST("Insertar")
    Call<Posts> createPost(@Body Posts posts);

    @POST("Login")
    Call<Vars> createPost(@Body Vars vars);

    @POST("HitoID")
    Call<HitoID> createPost(@Body HitoID hitoID);

    @POST("Transportes")
    Call<List<Transportes>> transportes(@Body Transportes transportes);

    @POST("TransportesCodigo")
    Call<TransportesCodigo> transportesCodigo(@Body TransportesCodigo transportesCodigo);

    @POST("HitoFactura")
    Call<HitoFactura> createPost(@Body HitoFactura hitoFactura);

    @POST("InsertarDatosFac")
    Call<InsertarDatosFac> createPost(@Body InsertarDatosFac insertarDatosFac);

    @POST("SucursalHitos")
    Call<SucursalHitos> createPost(@Body SucursalHitos sucursalHitos);

}
