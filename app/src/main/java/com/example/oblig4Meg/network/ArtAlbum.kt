package com.example.oblig4Meg.network

import com.squareup.moshi.Json

data class ArtAlbum(
    @Json val userId: Int = -1,
    @Json val id: Int = -1,
    @Json var title: String = "undefined",
)
