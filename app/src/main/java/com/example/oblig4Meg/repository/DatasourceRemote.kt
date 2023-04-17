package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.network.ArtApiService

class DatasourceRemote (private val artApiService: ArtApiService): IDatasource
{

    override suspend fun getAll(): List<Basket> {
        try {
            return artApiService.getPhotos()
        }
    }
}