package com.example.oblig4Meg.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.oblig4Meg.network.ArtArtist

@Entity(tableName = "basket_case")
data class Basket(
    @ColumnInfo(name = "albumId")
    val albumId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "URL")
    val url: String,

    @ColumnInfo(name = "thumbnailUrl")
    val thumbnailUrl: String,

    @ColumnInfo(name = "size")
    val size: String,

    @ColumnInfo(name = "bezel")
    val bezel: String,

    @ColumnInfo(name = "cost")
    val cost: Int,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "artist")
    val artist: String,

)