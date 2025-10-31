package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.week78_0706012410044_soal2.ui.model.AlbumDetailModel

@Composable
fun AlbumDetailCard(albumDetail: AlbumDetailModel?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color(0xFF3b3d3c)),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1b1f20))
    ) {
        Column() {
            AsyncImage(
                model = albumDetail?.thumbUrl,
                contentDescription = "Album Cover",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(380.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(15.dp)
            ) {
                Text(
                    text = albumDetail?.name ?: "Unknown Album",
                    color = Color(0xFFbcbaaa),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "${albumDetail?.year ?: "Unknown"} • ${albumDetail?.genre ?: "Unknown"}",
                    color = Color(0xFFbcbaaa),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = albumDetail?.description ?: "No description available",
                    color = Color(0xFFbcbaaa),
                    fontSize = 14.sp
                )

            }
        }
    }
}