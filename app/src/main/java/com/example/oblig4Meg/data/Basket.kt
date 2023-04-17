package com.example.oblig4Meg.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_case")
data class Basket(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "size")
    val size: String,
    @ColumnInfo(name = "bezel")
    val bezel: String,
    @ColumnInfo(name = "cost")
    val cost: Int,
    @ColumnInfo(name = "amount")
    val amount: Int,
    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String
)