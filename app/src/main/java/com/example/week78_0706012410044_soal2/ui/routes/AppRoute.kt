package com.example.week78_0706012410044_soal2.ui.routes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.week78_0706012410044_soal2.ui.view.AlbumDetailView
import com.example.week78_0706012410044_soal2.ui.view.ArtistView

enum class AppView(
    val title: String
) {
    Artist("Artist Explorer"),
    AlbumDetail("Album Detail")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoute() {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    var currentAlbumName by remember { mutableStateOf("Album Detail") }

    Scaffold(
        topBar = {
            when {
                currentRoute?.startsWith(AppView.AlbumDetail.name) == true ->
                    MyTopAppBar(
                        title = currentAlbumName,
                        navController = navController
                    )
                else ->
                    MyTopAppBar(
                        title = "Ariana Grande",
                        navController = navController
                    )
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination = "${AppView.Artist.name}/Ariana Grande"
        ) {
            composable("${AppView.Artist.name}/{artistName}") { backStackEntry ->
                val artistName =
                    backStackEntry.arguments?.getString("artistName") ?: "Ariana Grande"
                ArtistView(
                    artistName = artistName,
                    onAlbumClick = { album ->
                        currentAlbumName = album.name
                        navController.navigate("${AppView.AlbumDetail.name}/${album.id}")
                    }
                )
            }
            composable("${AppView.AlbumDetail.name}/{albumId}") { backStackEntry ->
                val albumId = backStackEntry.arguments?.getString("albumId") ?: ""
                AlbumDetailView(
                    albumId = albumId,
                    onAlbumNameLoaded = { albumName ->
                        currentAlbumName = albumName
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navController: NavHostController
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route
    val showBackButton = currentRoute?.startsWith(AppView.AlbumDetail.name) == true

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1b1f20)),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    if (showBackButton) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        Box(modifier = Modifier.width(48.dp))
                    }
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = title,
                            color = Color(0xFFbebdb4),
                            fontSize = 22.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Box(modifier = Modifier.width(48.dp))
                }
            }
        },
        navigationIcon = {}
    )
}