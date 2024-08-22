package com.example.finalcomplexivoestudiante

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import com.example.hotelcomplexivo.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EstudianteAdapter (context: Context, private val estudiantes: MutableList<Estudiantes>)
    : ArrayAdapter<Estudiantes>(context, 0, estudiantes) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_estudiante, parent, false)

        val estudiante = estudiantes[position]

        val tvcedula = view.findViewById<TextView>(R.id.tvcedula)
        val tvnombre = view.findViewById<TextView>(R.id.tvnombre)
        val tvapellido = view.findViewById<TextView>(R.id.tvapellido)
        val tvmateria = view.findViewById<TextView>(R.id.tvmateria)
        val btnEliminar = view.findViewById<Button>(R.id.btnEliminar)

        // Mostrar datos en el formato deseado
        tvcedula.text = "CEDULA: ${estudiante.cedula}"
        tvnombre.text = "NOMBRE: ${estudiante.nombre}"
        tvapellido.text = "APELLIDO: ${estudiante.apellido}"
        tvmateria.text = "MATERIA: ${estudiante.materia}"

        btnEliminar.setOnClickListener {
            eliminarEstudiante(estudiante.id, position)
        }

        return view
    }

    private fun eliminarEstudiante(id: Long, position: Int) {
        val apiService = ApiClient.retrofit // Usar la instancia de ApiService creada en ApiClient
        apiService.eliminarEstudiante(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    estudiantes.removeAt(position)
                    notifyDataSetChanged()
                } else {
                    // Manejar el error si la eliminación falla
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Manejar error
            }
        })
    }


}