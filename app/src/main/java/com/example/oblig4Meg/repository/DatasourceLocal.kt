package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.data.BasketDao
import com.example.oblig4Meg.network.ArtPhoto
import com.example.oblig4Meg.utils.asDomainModel

class DatasourceLocal internal constructor(private val basketDao: BasketDao): IDatasource{

    override suspend fun getAll(): List<ArtPhoto> {
        return basketDao.getItems().asDomainModel()
    }

    suspend fun insertList (basket: List<Basket>){
        basketDao.insertList(basket)
    }

    suspend fun insert(basket: Basket){
        basketDao.insert(basket)
    }

    suspend fun delete(basket: Basket){
        basketDao.delete(basket)
    }


    suspend fun deleteAll(){
        basketDao.deleteAll()
    }
}