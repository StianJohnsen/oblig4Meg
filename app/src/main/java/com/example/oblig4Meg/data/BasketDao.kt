package com.example.oblig4Meg.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photo: Basket)

    @Update
    suspend fun update(photo: Basket)

    @Delete
    suspend fun delete(photo: Basket)

    @Query("SELECT * FROM basket_case")
    suspend fun getItems(): List<Basket>

    @Query("DELETE FROM basket_case")
    suspend fun deleteAll()

}