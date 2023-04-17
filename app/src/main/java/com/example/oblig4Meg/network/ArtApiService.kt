package com.example.oblig4Meg.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ArtApiService {

    @GET("users/{id}")
    suspend fun getArtist(@Path("id") id: Int): ArtArtist

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: Int): ArtAlbum


    @GET("photos")
    suspend fun getPhotos(): List<ArtPhoto>


}

object ArtApi {
    val retrofitService: ArtApiService by lazy {
        retrofit.create(ArtApiService::class.java)
    }
}