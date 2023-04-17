package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.data.BasketDao

class DatasourceLocal internal constructor(private val basketDao: BasketDao): IDatasource{

    override suspend fun getAll(): List<Basket> {
        return basketDao.getItems().asDomainModel()
    }
}