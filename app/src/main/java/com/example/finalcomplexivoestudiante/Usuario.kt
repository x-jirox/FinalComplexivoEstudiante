package com.example.hotelcomplexivo

data class Usuario(
    val id: Long? = null,
    val username: String,
    val password: String,
    val nombres: String? = null,
    val rol: String? = null
)
