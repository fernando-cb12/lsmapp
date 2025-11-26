package com.example.lsmapp.ui.screens.senas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lsmapp.BottomNavBar
import com.example.lsmapp.R
import com.example.lsmapp.ui.screens.senas.components.SenaDetailDialog

@Composable
fun SenasScreen(
    viewModel: SenasViewModel = SenasViewModel(),
    onSenaClick: (Sena) -> Unit = {}
) {
    val senas by viewModel.senas.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredSenas = viewModel.getFilteredSenas()
    
    var selectedSena by remember { mutableStateOf<Sena?>(null) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFF47525E),
        bottomBar = { BottomNavBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Título
            Text(
                text = "Señas",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(vertical = 12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Grid de señas - 4 columnas con mejor espaciado
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(filteredSenas) { sena ->
                    SenaCard(
                        sena = sena,
                        onClick = { 
                            selectedSena = sena
                            onSenaClick(sena)
                        }
                    )
                }
            }
        }
        
        // Mostrar el dialog cuando se selecciona una seña
        selectedSena?.let { sena ->
            SenaDetailDialog(
                sena = sena,
                onDismiss = { selectedSena = null }
            )
        }
    }
}

@Composable
private fun SenaCard(
    sena: Sena,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFE8E8E8))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Si es la primera seña, mostrar imagen de ejemplo
        if (sena.id == 1) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = sena.nombre,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(16.dp))
            )
        } else {
            // Placeholder para las demás señas con el nombre
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = sena.nombre,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun SenasScreenPreview() {
    SenasScreen()
}
