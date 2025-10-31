package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week78_0706012410044_soal2.ui.model.AlbumModel
import com.example.week78_0706012410044_soal2.ui.viewmodel.ArtistViewModel

@Composable
fun ArtistView(
    artistName: String,
    onAlbumClick: (AlbumModel) -> Unit,
    viewModel: ArtistViewModel = viewModel()
) {
    val artistState by viewModel.artistState.collectAsState()
    val albumsState by viewModel.albumsState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    LaunchedEffect(artistName) {
        viewModel.loadArtistData(artistName)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272727)),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFFf6cc50))
        } else if (errorState != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Error: $errorState",
                    color = Color.Red,
                    fontSize = 16.sp
                )
            }
        } else if (artistState != null) {
            val artist = artistState
            val albums = albumsState
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (artist != null) {
                    ArtistHeader(artist = artist)
                }
                AlbumGrid(
                    albums = albums,
                    onAlbumClick = onAlbumClick
                )
            }
        }
    }
}