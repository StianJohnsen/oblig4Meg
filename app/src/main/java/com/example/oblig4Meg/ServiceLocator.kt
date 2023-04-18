package com.example.oblig4Meg

import android.content.Context
import com.example.oblig4Meg.data.BasketRoomDatabase
import com.example.oblig4Meg.network.ArtApi
import com.example.oblig4Meg.repository.AllRepository
import com.example.oblig4Meg.repository.BasketPhotos
import com.example.oblig4Meg.repository.ListOfPhotos


object ServiceLocator {

    var allRepository: AllRepository? = null

    fun provideBasketRepository(context: Context): AllRepository{
        synchronized(this){
            return allRepository ?: createBasketRepository(context)
        }
    }

    private fun createBasketRepository(context: Context): AllRepository{
        val basketData = createLocalDatasource(context)
        val listPhotosData = createRemoteDatasource(context)
        val newRepo = AllRepository(basketData,listPhotosData)
        allRepository = newRepo
        return newRepo
    }

    private fun createLocalDatasource(context: Context): BasketPhotos{
        val database: BasketRoomDatabase by lazy { BasketRoomDatabase.getDatabase(context) }
        return BasketPhotos(database.basketDao())
    }

    private fun createRemoteDatasource(context: Context):ListOfPhotos{
        val retrofitService = ArtApi.retrofitService
        return ListOfPhotos(retrofitService)
    }

}