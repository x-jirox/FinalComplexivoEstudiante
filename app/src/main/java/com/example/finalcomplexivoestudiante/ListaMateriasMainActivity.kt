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

class ListaMateriasMainActivity : AppCompatActivity() {

    private lateinit var listViewMaterias: ListView
    private lateinit var adapter: MateriaAdapter
    private val materias = mutableListOf<Materias>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_materias_main)



        listViewMaterias = findViewById(R.id.listViewMaterias)
        adapter = MateriaAdapter(this, materias)
        listViewMaterias.adapter = adapter

        fetchHabitaciones()
    }

    private fun fetchHabitaciones() {
        ApiClient.retrofit.listarMateria().enqueue(object : Callback<List<Materias>> {
            override fun onResponse(call: Call<List<Materias>>, response: Response<List<Materias>>) {
                if (response.isSuccessful) {
                    val fetchedHabitaciones = response.body() ?: return
                    materias.clear()
                    materias.addAll(fetchedHabitaciones)
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Materias>>, t: Throwable) {
                // Manejar error
            }
        })
    }

}