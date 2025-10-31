package com.example.week78_0706012410044_soal2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.week78_0706012410044_soal2.ui.viewmodel.AlbumDetailViewModel

@Composable
fun AlbumDetailView(
    albumId: String,
    onAlbumNameLoaded: (String) -> Unit = {},
    viewModel: AlbumDetailViewModel = viewModel()
) {
    val albumDetailState by viewModel.albumDetailState.collectAsState()
    val tracksState by viewModel.tracksState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorState by viewModel.errorState.collectAsState()

    LaunchedEffect(albumId) {
        viewModel.loadAlbumData(albumId)
    }

    LaunchedEffect(albumDetailState) {
        albumDetailState?.let { albumDetail ->
            onAlbumNameLoaded(albumDetail.name)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF272727)),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color(0xFFD65D0E))
        } else if (errorState != null) {
            Text(
                text = "Error: $errorState",
                color = Color(0xFFE74C3C),
                fontSize = 16.sp
            )
        } else if (albumDetailState != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF1E1E1E))
            ) {
                item {
                    AlbumDetailCard(albumDetailState!!)
                }
                item {
                    Text(
                        text = "Tracks",
                        color = Color(0xFFc6a73e),
                        fontSize = 20.sp,
                        modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
                    )
                }
                itemsIndexed(tracksState) { index, track ->
                    TrackItem(track = track, index = index + 1)
                }
                item { Spacer(modifier = Modifier.height(15.dp)) }
            }
        }
    }
}