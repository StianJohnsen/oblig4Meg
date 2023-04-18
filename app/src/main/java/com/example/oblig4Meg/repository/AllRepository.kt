package com.example.oblig4Meg.repository

import com.example.oblig4Meg.network.ArtPhoto
import com.example.oblig4Meg.utils.asBasket
import com.example.oblig4Meg.utils.asDatabaseModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AllRepository constructor(
    private val basketPhotos: BasketPhotos, //Remote
    private val listOfPhotos: ListOfPhotos //Database
){

    suspend fun fetchFromDB(): Flow<List<ArtPhoto>>{
        val photos = basketPhotos.getPhotos()
        return flow(){
            emit(photos)
        }
    }

    suspend fun insertIntoBasket(artPhoto: ArtPhoto){
        basketPhotos.insertIntoBasket(artPhoto.asBasket())
    }

    suspend fun deleteSingleBasket(artPhoto: ArtPhoto){
        basketPhotos.deleteBasket(artPhoto.asBasket())
    }

    suspend fun deleteBasket(){
        basketPhotos.deleteAllBasket()
    }

    suspend fun getListOfPhotos(): Flow<List<ArtPhoto>>{
        val photos = listOfPhotos.savePhotos()
        return flow(){
            emit(photos)
        }
    }


}