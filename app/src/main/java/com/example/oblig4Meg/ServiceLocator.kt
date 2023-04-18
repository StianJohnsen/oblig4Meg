package com.example.oblig4Meg

import android.content.Context
import com.example.oblig4Meg.data.BasketRoomDatabase
import com.example.oblig4Meg.network.ArtApi
import com.example.oblig4Meg.repositoryPhotos.BasketRepository

import com.example.oblig4Meg.repositoryPhotos.DatasourceRemote

object ServiceLocator {

    var basketRepository: BasketRepository? = null

    fun provideBasketRepository(context: Context): BasketRepository{
        synchronized(this){
            return basketRepository ?: createBasketRepository(context)
        }
    }





    private fun createRemoteDataSource(context: Context): DatasourceRemote{
        val retrofitService = ArtApi.retrofitService
        return DatasourceRemote(retrofitService)
    }

}