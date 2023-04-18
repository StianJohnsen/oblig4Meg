package com.example.oblig4Meg.utils

import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.network.ArtPhoto
import kotlin.math.cos

fun List<Basket>.asDomainModel(): List<ArtPhoto>{
    return map {
        ArtPhoto(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl,
            size = it.size,
            bezel = it.bezel,
            cost = it.cost,
            amount = it.amount,
            artist = it.artist

        )
    }
}

fun List<ArtPhoto>.asDatabaseModel(): List<Basket>{
    return map {
        Basket(
            albumId = it.albumId,
            id = it.id,
            title = it.title,
            url = it.url,
            thumbnailUrl = it.thumbnailUrl,
            size = it.size,
            bezel = it.bezel,
            cost = it.cost,
            amount = it.amount,
            artist = it.artist
        )
    }
}

fun ArtPhoto.asBasket(): Basket{
    return Basket(
        albumId = this.albumId,
        id = this.id,
        title = this.title,
        url = this.url,
        thumbnailUrl = this.thumbnailUrl,
        size = this.size,
        bezel = this.bezel,
        cost = this.cost,
        amount = this.amount,
        artist = this.artist
    )

}

