package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week78_0706012410044_soal2.ui.model.AlbumModel

@Composable
fun AlbumGrid(
    albums: List<AlbumModel>,
    onAlbumClick: (AlbumModel) -> Unit
) {
    if (albums.isNotEmpty()) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                text = "Albums",
                color = Color(0xFFcfceb8),
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 5.dp, bottom = 15.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(albums) { album ->
                    AlbumCard(
                        album = album,
                        onClick = { onAlbumClick(album) }
                    )
                }
            }
        }
    }
}