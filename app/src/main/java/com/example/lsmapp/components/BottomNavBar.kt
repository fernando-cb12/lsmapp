package com.example.lsmapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lsmapp.R
import com.example.lsmapp.ui.theme.Accent
import androidx.compose.material.icons.automirrored.filled.List
import com.example.lsmapp.ui.theme.LsmappTheme

enum class NavBarItem { LIST, SIGNS, PROFILE }

@Composable
fun NavBar(
    selectedItem: NavBarItem,
    onItemSelected: (NavBarItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(24.dp)), // Rounded corners for the navigation bar
        containerColor = Color.White,
        contentColor = MaterialTheme.colorScheme.onSurface // Default content color
    ) {
        NavigationBarItem(
            selected = selectedItem == NavBarItem.LIST,
            onClick = { onItemSelected(NavBarItem.LIST) },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "List") }, // Corrected deprecated icon usage
            label = { Text("List") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = Accent,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = Color.Transparent // No indicator color for selected item
            )
        )
        NavigationBarItem(
            selected = selectedItem == NavBarItem.SIGNS,
            onClick = { onItemSelected(NavBarItem.SIGNS) },
            icon = { Icon(painterResource(id = R.drawable.baseline_back_hand_24), contentDescription = "Signs") },
            label = { Text("Signs") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = Accent,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = selectedItem == NavBarItem.PROFILE,
            onClick = { onItemSelected(NavBarItem.PROFILE) },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Accent,
                unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                selectedTextColor = Accent,
                unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavBarPreview() {
    LsmappTheme {
        NavBar(selectedItem = NavBarItem.SIGNS) {}
    }
}
