package com.example.oblig4Meg.network

import com.squareup.moshi.Json


data class ArtPhoto(
    @Json(name = "albumId") val albumId: Int = -1,
    @Json(name = "id") var id: Int = -1,
    @Json(name = "title") val title: String = "undefined",
    @Json(name = "url") var url: String = "undefined",
    @Json(name = "thumbnailUrl") val thumbnailUrl: String = "undefined",
    var size: String = "undefined",
    var bezel: String = "undefined",
    var cost: Int = -1,
    var amount: Int = 1,
    var artist: String = "undefined",
)
