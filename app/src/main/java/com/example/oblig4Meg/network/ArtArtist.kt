package com.example.oblig4Meg.network

import com.squareup.moshi.Json

data class ArtArtist(
    @Json val id: Int = -1,
    @Json var name: String = "undefined",
    @Json val email: String = "undefined",
    @Json val phone: String = "undefined",
    @Json val website: String = "undefined",
)
