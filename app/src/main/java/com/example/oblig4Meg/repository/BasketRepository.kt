package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.network.ArtPhoto
import com.example.oblig4Meg.utils.asBasket
import com.example.oblig4Meg.utils.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BasketRepository constructor(
    private val datasourceLocal: DatasourceLocal,
    private val datasourceRemote: DatasourceRemote
){

    suspend fun getLocalBasket(): Flow<List<ArtPhoto>>{
        val basket = datasourceLocal.getAll()
        return flow() {
            emit(basket)
        }
    }


    suspend fun refreshBasket(): Flow<List<ArtPhoto>>{
        val basket = datasourceRemote.getAll()
        datasourceLocal.insertList(basket.asDatabaseModel())
        return getLocalBasket()
    }

    suspend fun deleteAllBasketDB(){
        datasourceLocal.deleteAll()
    }

    suspend fun deleteSingleBasketDB(artPhoto: ArtPhoto){
        datasourceLocal.delete(artPhoto.asBasket())
    }

}
