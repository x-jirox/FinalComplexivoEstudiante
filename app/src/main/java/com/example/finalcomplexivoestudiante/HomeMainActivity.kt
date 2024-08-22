package com.example.finalcomplexivoestudiante

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class HomeMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Configura los botones para la navegación
        val btnEstudiantes = findViewById<Button>(R.id.btnestudiantes)
        val btnMaterias = findViewById<Button>(R.id.btnmaterias)

        btnEstudiantes.setOnClickListener {
            val intent = Intent(this, ListaEstudiantesMainActivity::class.java)
            startActivity(intent)
        }

        btnMaterias.setOnClickListener {
            val intent = Intent(this, ListaMateriasMainActivity::class.java)
            startActivity(intent)
        }
    }
}
