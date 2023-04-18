package com.example.oblig4Meg.repository

import androidx.lifecycle.MutableLiveData
import com.example.oblig4Meg.network.ArtApiService
import com.example.oblig4Meg.network.ArtPhoto
import java.io.IOException

/*

Retrieves the list of ArtPhotos from a retrofitservice. This is then saved into a
variable housed within the ViewModel.
 */


class ListOfPhotos(private val artApiService: ArtApiService) {

    suspend fun savePhotos():List<ArtPhoto>{
        try {
            return artApiService.getPhotos()
        }catch (exception: IOException){
            throw exception
        }
    }
}