package com.example.oblig4Meg.repositoryPhotos

import com.example.oblig4Meg.network.ArtPhoto

interface IDatasource {

    suspend fun getAll(): List<ArtPhoto>

}