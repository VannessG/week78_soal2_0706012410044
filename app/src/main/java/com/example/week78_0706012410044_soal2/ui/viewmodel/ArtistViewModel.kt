package com.example.week78_0706012410044_soal2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week78_0706012410044_soal2.data.repository.ArtistRepository
import com.example.week78_0706012410044_soal2.ui.model.AlbumModel
import com.example.week78_0706012410044_soal2.ui.model.ArtistModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArtistViewModel : ViewModel() {
    private val repository = ArtistRepository()

    private val _artistState = MutableStateFlow<ArtistModel?>(null)
    val artistState: StateFlow<ArtistModel?> = _artistState.asStateFlow()

    private val _albumsState = MutableStateFlow<List<AlbumModel>>(emptyList())
    val albumsState: StateFlow<List<AlbumModel>> = _albumsState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    fun loadArtistData(artistName: String) {
        _isLoading.value = true
        _errorState.value = null
        _artistState.value = null
        _albumsState.value = emptyList()

        viewModelScope.launch {
            val artist = repository.getArtist(artistName)
            val albums = repository.getAlbums(artistName)

            _artistState.value = artist
            _albumsState.value = albums

            if (artist == null && albums.isEmpty()) {
                _errorState.value = "No artist or albums found"
            } else if (albums.isEmpty()) {
                _errorState.value = "No albums found"
            }
            _isLoading.value = false
        }
    }
}