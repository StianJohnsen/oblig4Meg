package com.example.oblig4Meg.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: Basket)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(basketList: List<Basket>)

    @Update
    suspend fun update(photo: Basket)

    @Delete
    suspend fun delete(photo: Basket)

    @Query("SELECT * FROM basket_case")
    suspend fun getItems(): List<Basket>

    @Query("DELETE FROM basket_case")
    suspend fun deleteAll()

}