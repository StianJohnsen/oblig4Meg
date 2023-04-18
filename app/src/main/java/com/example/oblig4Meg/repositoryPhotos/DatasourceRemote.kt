package com.example.oblig4Meg.repositoryPhotos

import com.example.oblig4Meg.network.ArtApiService
import com.example.oblig4Meg.network.ArtPhoto
import java.io.IOException

class DatasourceRemote (private val artApiService: ArtApiService): IDatasource
{

    override suspend fun getAll(): List<ArtPhoto> {
        try {
            return artApiService.getPhotos()
        }catch (exception: IOException){
            throw exception
        }
    }
}