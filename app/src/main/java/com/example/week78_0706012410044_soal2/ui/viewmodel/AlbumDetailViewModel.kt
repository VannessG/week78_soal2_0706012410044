package com.example.week78_0706012410044_soal2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week78_0706012410044_soal2.data.repository.ArtistRepository
import com.example.week78_0706012410044_soal2.ui.model.AlbumDetailModel
import com.example.week78_0706012410044_soal2.ui.model.TrackModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumDetailViewModel : ViewModel() {
    private val repository = ArtistRepository()
    private val _albumDetailState = MutableStateFlow<AlbumDetailModel?>(null)
    val albumDetailState: StateFlow<AlbumDetailModel?> = _albumDetailState.asStateFlow()

    private val _tracksState = MutableStateFlow<List<TrackModel>>(emptyList())
    val tracksState: StateFlow<List<TrackModel>> = _tracksState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState.asStateFlow()

    fun loadAlbumData(albumId: String) {
        _isLoading.value = true
        _errorState.value = null
        _albumDetailState.value = null
        _tracksState.value = emptyList()

        viewModelScope.launch {
            val albumDetail = repository.getAlbumDetail(albumId)
            val tracks = repository.getAlbumTracks(albumId)

            _albumDetailState.value = albumDetail
            _tracksState.value = tracks
            if (albumDetail == null) {
                _errorState.value = "Album not found"
            }
            _isLoading.value = false
        }
    }
}