package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.data.BasketDao
import com.example.oblig4Meg.network.ArtPhoto
import com.example.oblig4Meg.utils.asDomainModel

/*
Inserts ArtPhoto objects into a database. The ArtPhoto object gets inserted from the
list of photos, when selecting them.
 */

class BasketPhotos internal constructor (private val basketDao: BasketDao){
    suspend fun getPhotos(): List<ArtPhoto>{
        return basketDao.getItems().asDomainModel()
    }

    suspend fun insertIntoBasket(basket: Basket){
        basketDao.insert(basket)
    }

    suspend fun deleteAllBasket(){
        basketDao.deleteAll()
    }

    suspend fun deleteBasket(basket: Basket){
        basketDao.delete(basket)
    }
}