package com.example.oblig4Meg.repository

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.network.ArtPhoto

interface IDatasource {

    suspend fun getAll(): List<ArtPhoto>

}