package com.example.finalcomplexivoestudiante

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hotelcomplexivo.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaEstudiantesMainActivity : AppCompatActivity() {

    private lateinit var listViewEstudiante: ListView
    private lateinit var adapter: EstudianteAdapter
    private val estudiante = mutableListOf<Estudiantes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_estudiantes_main)

        val crearEstudianteButton: Button = findViewById(R.id.button)
        crearEstudianteButton.setOnClickListener {
            val intent = Intent(this, CrearEstudianteMain::class.java)
            startActivity(intent)
        }

        listViewEstudiante = findViewById(R.id.listViewEstudiante)
        adapter = EstudianteAdapter(this, estudiante)
        listViewEstudiante.adapter = adapter

        fetchHabitaciones()
    }

    private fun fetchHabitaciones() {
        ApiClient.retrofit.listarEstudiantes().enqueue(object : Callback<List<Estudiantes>> {
            override fun onResponse(call: Call<List<Estudiantes>>, response: Response<List<Estudiantes>>) {
                if (response.isSuccessful) {
                    val fetchedEstudiantes = response.body() ?: return
                    estudiante.clear()
                    estudiante.addAll(fetchedEstudiantes)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Estudiantes>>, t: Throwable) {
                // Manejar error
            }
        })
    }

}