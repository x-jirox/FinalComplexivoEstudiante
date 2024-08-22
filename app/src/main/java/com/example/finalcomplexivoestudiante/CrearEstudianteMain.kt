package com.example.finalcomplexivoestudiante

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.hotelcomplexivo.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CrearEstudianteMain : AppCompatActivity() {

    private lateinit var etCedula: EditText
    private lateinit var etNombre: EditText
    private lateinit var etApellido: EditText
    private lateinit var spinnerMateria: Spinner
    private lateinit var btnCrearEstudiante: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_estudiante_main)

        etCedula = findViewById(R.id.etcedula)
        etNombre = findViewById(R.id.etnombre)
        etApellido = findViewById(R.id.etapellido)
        spinnerMateria = findViewById(R.id.spinnermateria)
        btnCrearEstudiante = findViewById(R.id.btnCrearEstudiante)

        // Configurar Spinner para tipos de habitación
        ApiClient.retrofit.listarMateria()
            .enqueue(object : Callback<List<Materias>> {
                override fun onResponse(
                    call: Call<List<Materias>>,
                    response: Response<List<Materias>>
                ) {
                    if (response.isSuccessful) {
                        val materias = response.body() ?: return
                        val nombres = materias.map { it.materia }
                        val ids = materias.map { it.id }

                        val adapter = ArrayAdapter(
                            this@CrearEstudianteMain,
                            android.R.layout.simple_spinner_item,
                            nombres
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        spinnerMateria.adapter = adapter

                        // Guarda los IDs en el tag del Spinner
                        spinnerMateria.tag = ids
                    }
                }

                override fun onFailure(call: Call<List<Materias>>, t: Throwable) {
                    // Manejar error
                }
            })



        btnCrearEstudiante.setOnClickListener {
            val cedula = etCedula.text.toString()
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val materias = spinnerMateria.selectedItem.toString()

            if (cedula != null) {
                // Obtén el ID del tipo de habitación del tag del Spinner
                val materiasid = spinnerMateria.tag as? List<Long>
                val allmateriasid = materiasid?.get(spinnerMateria.selectedItemPosition)

                if (allmateriasid != null) {
                    val materia = Materias(
                        id = allmateriasid,
                        materia =  materias
                    )

                    val estudiante = Estudiantes(
                        id = 0,  // El servidor debería asignar un ID
                        cedula = cedula,
                        nombre = nombre,
                        apellido = apellido,
                        materia = materia
                    )

                    ApiClient.retrofit.crearEstudiante(estudiante)
                        .enqueue(object : Callback<Estudiantes> {
                            override fun onResponse(
                                call: Call<Estudiantes>,
                                response: Response<Estudiantes>
                            ) {
                                if (response.isSuccessful) {
                                    // Habitacion creada exitosamente
                                    println("Estudiante creada exitosamente")

                                    // Crear un Intent para redirigir a ListaHabitacionesMain
                                    val intent = Intent(this@CrearEstudianteMain, ListaEstudiantesMainActivity::class.java)
                                    startActivity(intent)
                                    finish() // Opcional: para cerrar la actividad actual
                                } else {
                                    // Respuesta no exitosa
                                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                                }
                            }

                            override fun onFailure(call: Call<Estudiantes>, t: Throwable) {
                                // Manejar error
                                println("Error en la solicitud: ${t.message}")
                            }
                        })
                } else {
                    // Manejar error de ID de tipo de habitación no encontrado
                    println("Tipo de habitación no encontrado")
                }
            } else {
                // Manejar error de precio inválido
                println("Precio por noche inválido")
            }
        }

    }
}