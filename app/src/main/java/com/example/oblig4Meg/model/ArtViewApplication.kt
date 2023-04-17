package com.example.oblig4Meg.model

import android.app.Application
import com.example.oblig4Meg.data.BasketRoomDatabase

class ArtViewApplication: Application(){
    val database: BasketRoomDatabase by lazy { BasketRoomDatabase.getDatabase(this) }
}