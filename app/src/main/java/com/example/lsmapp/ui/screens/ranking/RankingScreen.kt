package com.example.lsmapp.ui.screens.ranking

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.MilitaryTech
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class UserScore(val name: String, val score: Int)

@Composable
fun RankingScreen(modifier: Modifier = Modifier) {
    val users = listOf(
        UserScore("Usuario A", 345),
        UserScore("Usuario B", 200),
        UserScore("Usuario C", 155),
        UserScore("Usuario D", 120),
        UserScore("Usuario E", 115),
        UserScore("Usuario F", 85),
        UserScore("Usuario G", 55)
    )

    Column(modifier = modifier.padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)) {
            Text("Ranking", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Icon(Icons.Default.ArrowDropDown, contentDescription = "Filter", tint = Color.White)
        }

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(users) { index, user ->
                RankingItem(rank = index + 1, user = user)
            }
        }
    }
}

@Composable
fun RankingItem(rank: Int, user: UserScore) {
    val (medalInfo, border, scoreColor) = when (rank) {
        1 -> Triple(Pair(Icons.Filled.MilitaryTech, Color(0xFFFFD700)), BorderStroke(2.dp, Color(0xFFFFD700)), Color(0xFFFFD700))
        2 -> Triple(Pair(Icons.Filled.MilitaryTech, Color(0xFFC0C0C0)), BorderStroke(2.dp, Color(0xFFC0C0C0)), Color(0xFFC0C0C0))
        3 -> Triple(Pair(Icons.Filled.MilitaryTech, Color(0xFFCD7F32)), BorderStroke(2.dp, Color(0xFFCD7F32)), Color(0xFFCD7F32))
        else -> Triple(null, BorderStroke(1.dp, Color(0x99008080)), Color.White)
    }

    val itemShape = RoundedCornerShape(16.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4A4A4A), itemShape)
            .border(border, itemShape)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (medalInfo != null) {
                Icon(imageVector = medalInfo.first, contentDescription = "Medal", tint = medalInfo.second)
                Text(text = user.name, color = Color.White, fontSize = 18.sp)
            } else {
                Text(text = "$rank.- ${user.name}", color = Color.White, fontSize = 18.sp)
            }
        }
        Text(
            text = user.score.toString(),
            color = scoreColor,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RankingScreenPreview() {
    RankingScreen()
}
