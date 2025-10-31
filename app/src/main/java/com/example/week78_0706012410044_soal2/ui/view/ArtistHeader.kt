package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week78_0706012410044_soal2.ui.model.ArtistModel

@Composable
fun ArtistHeader(artist: ArtistModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 0.dp)
    ) {
        AsyncImage(
            model = artist.thumbUrl,
            contentDescription = "Artist",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(15.dp))
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 40.dp, start = 15.dp)
        ) {
            Text(
                text = artist.name,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 15.dp, start = 15.dp)
        ) {
            Text(
                text = artist.genre,
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}