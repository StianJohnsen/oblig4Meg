package com.example.oblig4Meg.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Basket::class], version = 3, exportSchema = false)
abstract class BasketRoomDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao

    companion object {
        @Volatile
        private var INSTANCE: BasketRoomDatabase? = null

        fun getDatabase(context: Context): BasketRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BasketRoomDatabase::class.java,
                    "basket_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}