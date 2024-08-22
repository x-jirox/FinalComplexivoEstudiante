package com.example.hotelcomplexivo

import com.example.finalcomplexivoestudiante.Estudiantes
import com.example.finalcomplexivoestudiante.Materias
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    //login
    @POST("api/users/login")
    fun login(@Body loginRequest: Usuario): Call<Usuario>

    //listar habitaciones
    @GET("api/users/listarEstudiantes")
    fun listarEstudiantes(): Call<List<Estudiantes>>

    //listar tipo de habitaciones
    @GET("api/users/listamateria")
    fun listarMateria(): Call<List<Materias>>

    //crear habitacion
    @POST("api/users/crear")
    fun crearEstudiante(@Body habitacion: Estudiantes): Call<Estudiantes>


    @PUT("api/users/editar/{id}")
    fun editarEstudiante(@Path("id") id: Long, @Body estudiante: Estudiantes): Call<Estudiantes>

    @DELETE("api/users/eliminar/{id}")
    fun eliminarEstudiante(@Path("id") id: Long): Call<Void>
}
