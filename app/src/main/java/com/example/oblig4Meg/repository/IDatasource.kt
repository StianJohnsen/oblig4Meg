package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket

interface IDatasource {

    suspend fun getAll(): List<Basket>

}