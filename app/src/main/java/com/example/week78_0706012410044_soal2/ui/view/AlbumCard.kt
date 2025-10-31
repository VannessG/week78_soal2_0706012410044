package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week78_0706012410044_soal2.ui.model.AlbumModel

@Composable
fun AlbumCard(album: AlbumModel, onClick: () -> Unit) {
    Card(
        border = BorderStroke(1.dp, Color(0xFF3b3d3c)),
        modifier = Modifier
            .width(160.dp)
            .clickable { onClick() }
            .clip(RoundedCornerShape(10.dp))
    ) {
        Column(
            modifier = Modifier.background(Color(0xFF1b1f20))
        ) {
            AsyncImage(
                model = album.thumbUrl,
                contentDescription = album.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .clip(RoundedCornerShape(10))
            )
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                Text(
                    text = album.name,
                    color = Color(0xffcac9b4),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "${album.year ?: "Unknown"} • ${album.genre ?: "Unknown"}",
                    color = Color(0xffcac9b4),
                    fontSize = 12.sp
                )
            }
        }
    }
}