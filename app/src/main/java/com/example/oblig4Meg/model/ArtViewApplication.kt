package com.example.oblig4Meg.model

import android.app.Application
import com.example.oblig4Meg.ServiceLocator
import com.example.oblig4Meg.data.BasketRoomDatabase
import com.example.oblig4Meg.repository.BasketRepository

class ArtViewApplication: Application(){
    val basketRepository: BasketRepository
        get() = ServiceLocator.provideBasketRepository(this)
}