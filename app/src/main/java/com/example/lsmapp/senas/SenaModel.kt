package com.example.lsmapp.senas

data class Sena(
    val id: Int,
    val nombre: String,
    val imagenUrl: String,
    val videoUrl: String? = null,
    val descripcion: String? = null
)

object SenasData {
    val senasMock = listOf(
        Sena(
            id = 1,
            nombre = "Hola",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Saludo básico en LSM"
        ),
        Sena(
            id = 2,
            nombre = "Gracias",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Expresión de agradecimiento"
        ),
        Sena(
            id = 3,
            nombre = "Por favor",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Solicitud cortés"
        ),
        Sena(
            id = 4,
            nombre = "Adiós",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Despedida"
        ),
        Sena(
            id = 5,
            nombre = "Sí",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Afirmación"
        ),
        Sena(
            id = 6,
            nombre = "No",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Negación"
        ),
        Sena(
            id = 7,
            nombre = "Casa",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Lugar de residencia"
        ),
        Sena(
            id = 8,
            nombre = "Familia",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Grupo familiar"
        ),
        Sena(
            id = 9,
            nombre = "Amigo",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Persona cercana"
        ),
        Sena(
            id = 10,
            nombre = "Comer",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Acción de alimentarse"
        ),
        Sena(
            id = 11,
            nombre = "Beber",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Acción de tomar líquidos"
        ),
        Sena(
            id = 12,
            nombre = "Dormir",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Descansar durante la noche"
        ),
        Sena(
            id = 13,
            nombre = "Escuela",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Lugar de estudio"
        ),
        Sena(
            id = 14,
            nombre = "Trabajo",
            imagenUrl = "https://via.placeholder.com/150",
            descripcion = "Actividad laboral"
        )
    )
}