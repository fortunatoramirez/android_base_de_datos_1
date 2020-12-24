package com.example.equipomedico1.API;

import com.example.equipomedico1.Modelo.EquipoMedico;
import java.util.List;


import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfazBuscarAPI {
    @GET("equipo")
    Observable<List<EquipoMedico>> obtenerEquipoMedico();

    @POST("buscar")
    @FormUrlEncoded
    Observable<List<EquipoMedico>> buscarEquipoMedico(@Field("busqueda") String equipoBuscar);

}
