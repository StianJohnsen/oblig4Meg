package com.example.oblig4Meg

import android.content.Context
import com.example.oblig4Meg.data.BasketRoomDatabase
import com.example.oblig4Meg.network.ArtApi
import com.example.oblig4Meg.network.ArtApiService
import com.example.oblig4Meg.repository.BasketRepository
import com.example.oblig4Meg.repository.DatasourceLocal
import com.example.oblig4Meg.repository.DatasourceRemote

object ServiceLocator {

    var basketRepository: BasketRepository? = null

    fun provideBasketRepository(context: Context): BasketRepository{
        synchronized(this){
            return basketRepository ?: createBasketRepository(context)
        }
    }

    private fun createBasketRepository(context: Context): BasketRepository{
        val localDatasource = createLocalDataSource(context)
        val remoteDatasource = createRemoteDataSource(context)
        val newRepository = BasketRepository(localDatasource,remoteDatasource)
        basketRepository = newRepository
        return basketRepository as BasketRepository
    }

    private fun createLocalDataSource(context: Context): DatasourceLocal{
        val database: BasketRoomDatabase by lazy { BasketRoomDatabase.getDatabase(context) }
        return DatasourceLocal(database.basketDao())
    }

    private fun createRemoteDataSource(context: Context): DatasourceRemote{
        val retrofitService = ArtApi.retrofitService
        return DatasourceRemote(retrofitService)
    }

}