package com.example.oblig4Meg.model

import android.app.Application
import com.example.oblig4Meg.ServiceLocator
import com.example.oblig4Meg.repository.AllRepository

class ArtViewApplication: Application(){
    val basketRepository: AllRepository
        get() = ServiceLocator.provideBasketRepository(this)
}