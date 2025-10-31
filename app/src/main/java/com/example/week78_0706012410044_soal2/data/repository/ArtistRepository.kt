package com.example.week78_0706012410044_soal2.data.repository

import com.example.week78_0706012410044_soal2.data.container.ArtistContainer.artistService
import com.example.week78_0706012410044_soal2.ui.model.AlbumDetailModel
import com.example.week78_0706012410044_soal2.ui.model.AlbumModel
import com.example.week78_0706012410044_soal2.ui.model.ArtistModel
import com.example.week78_0706012410044_soal2.ui.model.TrackModel

class ArtistRepository {
    suspend fun getArtist(artistName: String): ArtistModel? {
        val response = artistService.searchArtist(artistName)
        if (response.artists != null && response.artists.isNotEmpty()) {
            val artist = response.artists[0]
            return ArtistModel(
                id = artist.idArtist,
                name = artist.strArtist,
                genre = artist.strGenre,
                thumbUrl = artist.strArtistThumb
            )
        }
        return null
    }

    suspend fun getAlbums(artistName: String): List<AlbumModel> {
        val response = artistService.searchAlbum(artistName)
        if (response.album == null || response.album.isEmpty()) {
            return emptyList()
        }

        val albumList = mutableListOf<AlbumModel>()
        for (albumDto in response.album) {
            if (!albumDto.idAlbum.isNullOrEmpty() && !albumDto.strAlbum.isNullOrEmpty()) {
                val albumModel = AlbumModel(
                    id = albumDto.idAlbum,
                    name = albumDto.strAlbum,
                    artist = albumDto.strArtist,
                    year = albumDto.intYearReleased ?: "Unknown",
                    genre = albumDto.strGenre ?: "Unknown",
                    thumbUrl = albumDto.strAlbumThumb
                )
                albumList.add(albumModel)
            }
        }
        return albumList
    }

    suspend fun getAlbumDetail(albumId: String): AlbumDetailModel? {
        val response = artistService.getAlbumDetail(albumId)
        return if (response.album != null && response.album.isNotEmpty()) {
            val album = response.album[0]
            AlbumDetailModel(
                id = album.idAlbum,
                name = album.strAlbum,
                artist = album.strArtist,
                year = album.intYearReleased ?: "Unknown",
                genre = album.strGenre ?: "Unknown",
                thumbUrl = album.strAlbumThumb,
                description = album.strDescriptionEN ?: "No description available"
            )
        } else {
            null
        }
    }

    suspend fun getAlbumTracks(albumId: String): List<TrackModel> {
        val response = artistService.getAlbumTracks(albumId)
        return response.track?.map { track ->
            TrackModel(
                id = track.idTrack,
                name = track.strTrack,
                album = track.strAlbum,
                duration = track.intDuration
            )
        } ?: emptyList()
    }
}