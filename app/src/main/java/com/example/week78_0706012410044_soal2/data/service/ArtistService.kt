package com.example.week78_0706012410044_soal2.data.service

import com.example.week78_0706012410044_soal2.data.dto.ResponseAlbum
import com.example.week78_0706012410044_soal2.data.dto.ResponseAlbumDetail
import com.example.week78_0706012410044_soal2.data.dto.ResponseArtist
import com.example.week78_0706012410044_soal2.data.dto.ResponseTrack
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {
    @GET("search.php")
    suspend fun searchArtist(
        @Query("s") artistName: String
    ): ResponseArtist

    @GET("searchalbum.php")
    suspend fun searchAlbum(
        @Query("s") artistName: String
    ): ResponseAlbum

    @GET("album.php")
    suspend fun getAlbumDetail(
        @Query("m") albumId: String
    ): ResponseAlbumDetail

    @GET("track.php")
    suspend fun getAlbumTracks(
        @Query("m") albumId: String
    ): ResponseTrack
}