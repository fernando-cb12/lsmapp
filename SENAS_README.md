# Sección de Señas - Documentación

## Archivos Creados

### 1. `SenaModel.kt`
Define el modelo de datos para las señas y contiene datos de ejemplo (mock).

**Estructura:**
- `Sena`: Data class con id, nombre, imagenUrl, videoUrl y descripción
- `SenasData`: Objeto que contiene una lista de 14 señas de ejemplo

### 2. `SenasViewModel.kt`
ViewModel que maneja el estado de la lista de señas y búsqueda.

**Funcionalidades:**
- Carga de señas desde los datos mock
- Manejo de búsqueda/filtrado
- Estados observables con StateFlow

### 3. `SenasScreen.kt`
Pantalla principal que muestra la cuadrícula de señas.

**Características:**
- Grid 3x3 de tarjetas de señas
- Título "Señas" 
- Integración con BottomNavBar
- Diseño responsive con LazyVerticalGrid
- Fondo gris oscuro (#47525E) consistente con el diseño

### 4. `SenaDetailScreen.kt`
Pantalla de detalle individual de cada seña.

**Características:**
- Botón de regresar
- Imagen/video grande de la seña
- Descripción de la seña
- Botones de "Practicar" y "Favorito"
- Diseño limpio y moderno

## Integración en MainActivity

Para integrar estas pantallas en tu app, deberás actualizar tu `MainActivity.kt` para incluir navegación. Aquí hay un ejemplo básico:

```kotlin
package com.example.lsmapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.lsmapp.ui.screens.senas.Sena
import com.example.lsmapp.ui.screens.senas.SenaDetailScreen
import com.example.lsmapp.ui.screens.senas.SenasScreen
import com.example.lsmapp.ui.screens.senas.SenasViewModel
import com.example.lsmapp.ui.theme.LsmappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LsmappTheme {
                SenasNavigationApp()
            }
        }
    }
}

@Composable
fun SenasNavigationApp() {
    var currentScreen by remember { mutableStateOf("list") }
    var selectedSena by remember { mutableStateOf<Sena?>(null) }
    val viewModel = remember { SenasViewModel() }

    when (currentScreen) {
        "list" -> SenasScreen(
            viewModel = viewModel,
            onSenaClick = { sena ->
                selectedSena = sena
                currentScreen = "detail"
            }
        )
        "detail" -> selectedSena?.let { sena ->
            SenaDetailScreen(
                sena = sena,
                onBackClick = { currentScreen = "list" }
            )
        }
    }
}
```

## Características del Diseño

### Colores Utilizados
- **Fondo principal**: #47525E (Gris oscuro)
- **Tarjetas de señas**: #D9D9D9 (Gris claro)
- **Botón destacado**: #008080 (Verde azulado)
- **Texto principal**: Blanco

### Componentes Reutilizados
- ✅ `BottomNavBar` - Barra de navegación inferior
- ✅ Colores consistentes con LoginScreen
- ✅ Tipografía Material 3
- ✅ Rounded corners y sombras similares

## Próximos Pasos

1. **Agregar imágenes reales**: Reemplaza los placeholders con imágenes reales de las señas
2. **Implementar reproducción de videos**: Añade funcionalidad para reproducir videos de las señas
3. **Conectar con backend**: Reemplaza los datos mock con datos reales de una API o base de datos
4. **Añadir búsqueda**: Implementa una barra de búsqueda funcional
5. **Favoritos**: Implementa la funcionalidad de guardar señas favoritas
6. **Modo práctica**: Crea una pantalla interactiva para practicar las señas

## Dependencias Agregadas

```kotlin
implementation("io.coil-kt:coil-compose:2.5.0") // Para cargar imágenes desde URLs
```

## Notas Técnicas

- Las señas mock tienen URLs de placeholder (https://via.placeholder.com/150)
- La primera seña muestra el logo como ejemplo
- El diseño es completamente responsive
- La navegación básica está implementada pero puede mejorarse con Navigation Compose
- Los estados se manejan con StateFlow para mejor rendimiento
