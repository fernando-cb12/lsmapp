package com.example.lsmapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lsmapp.ui.theme.LsmappTheme

@Composable
fun ProfileScreen(onLogout: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(PrimaryDarkGrey)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Foto de perfil
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(Color(0xFF5B6673))
                .border(3.dp, Color(0xFF6B7683), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Avatar",
                tint = Color(0xFF9BA3AB),
                modifier = Modifier.size(60.dp)
            )

            // Botón de editar foto
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { /* TODO: Editar foto */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar foto",
                    tint = Color.Black,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Campo: Nombre de Usuario
        ProfileField(
            label = "Nombre de Usuario",
            value = "JHDelacruz777",
            onEditClick = { /* TODO: Editar nombre */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo: Correo Electrónico
        ProfileField(
            label = "Correo Electrónico",
            value = "JHDelacruz777@gmail.com",
            onEditClick = { /* TODO: Editar correo */ }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Campo: Contraseña
        ProfileField(
            label = "Contraseña",
            value = "••••••••••••••••••",
            onEditClick = { /* TODO: Editar contraseña */ }
        )

        Spacer(modifier = Modifier.weight(1f))

        // Botón Cerrar Sesión
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFFFF5252)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Cerrar Sesión",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun ProfileField(
    label: String,
    value: String,
    onEditClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
//                .background(LogoContainerColor, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                color = Color.White,
                fontSize = 16.sp
            )

            IconButton(
                onClick = onEditClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Editar $label",
                    tint = Color(0xFF9BA3AB),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenPreview() {
    LsmappTheme {
        ProfileScreen(onLogout = {})
    }
}