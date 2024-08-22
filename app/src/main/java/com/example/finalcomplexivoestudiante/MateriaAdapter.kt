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

class MateriaAdapter (context: Context, private val materias: MutableList<Materias>)
    : ArrayAdapter<Materias>(context, 0, materias) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_materias, parent, false)

        val materia = materias[position]

        val tvmateria= view.findViewById<TextView>(R.id.tvmateria)


        // Mostrar datos en el formato deseado
        tvmateria.text = "MATERIA: ${materia.materia}"


        return view
    }

}