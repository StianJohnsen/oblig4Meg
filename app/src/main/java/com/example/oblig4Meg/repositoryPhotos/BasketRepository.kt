package com.example.oblig4Meg.repositoryPhotos

import com.example.oblig4Meg.network.ArtPhoto
import com.example.oblig4Meg.utils.asBasket
import com.example.oblig4Meg.utils.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketRepository constructor(
    private val datasourceRemote: DatasourceRemote
){



    suspend fun refreshBasket(): List<ArtPhoto>{
        val photos = datasourceRemote.getAll()
        return photos


    }

}
