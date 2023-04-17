package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.data.BasketDao

class DatasourceLocal internal constructor(private val basketDao: BasketDao): IDatasource{

    override suspend fun getAll(): List<Basket> {
        return basketDao.getItems()
    }

    suspend fun insert (basket: Basket){
        basketDao.insert(basket)
    }

    suspend fun delete(basket: Basket){
        basketDao.delete(basket)
    }


    suspend fun deleteAll(){
        basketDao.deleteAll()
    }
}