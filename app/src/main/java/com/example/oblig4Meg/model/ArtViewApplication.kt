package com.example.oblig4Meg.model

import android.app.Application
import com.example.oblig4Meg.ServiceLocator
import com.example.oblig4Meg.repositoryPhotos.BasketRepository

class ArtViewApplication: Application(){
    val basketRepository: BasketRepository
        get() = ServiceLocator.provideBasketRepository(this)
}