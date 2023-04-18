package com.example.oblig4Meg.model

import android.service.autofill.Transformation
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.oblig4Meg.data.Basket
import com.example.oblig4Meg.data.BasketDao
import com.example.oblig4Meg.network.*
import com.example.oblig4Meg.repository.BasketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException


private const val PRICE_PER_PHOTO = 100.00


enum class ArtApiStatus { LOADING, ERROR, DONE }

class ArtViewModel(private val repository: BasketRepository) : ViewModel() {

    // Sannhetkilden
    private val _basket = MutableLiveData<List<ArtPhoto>>()
    val basket: LiveData<List<ArtPhoto>>
        get() = _basket

    private var _selectedBasket = MutableLiveData<ArtPhoto?>(null)
    val selectedBasket: LiveData<String> = _selectedBasket.map {
        if(it!=null)
            "Valgt: " + it.id.toString()
        else
            "Valgt "
    }

    private var _currentPictureId = MutableLiveData<Int>()
    val currentPictureId: LiveData<Int> = _currentPictureId

    private var _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private var _status = MutableLiveData<ArtApiStatus>()
    val status: LiveData<ArtApiStatus> = _status

    private var _photos = MutableLiveData<List<ArtPhoto>>()
    val photos: LiveData<List<ArtPhoto>> = _photos

    private var _album = MutableLiveData<ArtAlbum>()
    val album: LiveData<ArtAlbum> = _album

    private var _artist = MutableLiveData<ArtArtist>()
    val artist: LiveData<ArtArtist> = _artist

    private var _single_photo = MutableLiveData<ArtPhoto>()
    val single_photo: LiveData<ArtPhoto> = _single_photo

    private var _photo_basket = MutableLiveData<MutableList<ArtPhoto>>(mutableListOf())

    val photo_basket: LiveData<MutableList<ArtPhoto>> = _photo_basket

    init {
        //getLocalBasket()
        refreshBasket()
    }


//    private fun insertItem(photo: Basket) {
//        viewModelScope.launch {
//            basketDao.insert(photo)
//        }
//    }

    fun refreshBasket(){
        viewModelScope.launch {
            try {
                repository.refreshBasket()
                    .collect(){
                        _basket.postValue(it)
                    }
            }catch (exception: IOException){
                repository.getLocalBasket()
                    .collect(){
                        _basket.postValue(it)
                    }
            }
        }
    }

    fun getLocalBasket(){
        viewModelScope.launch {
            repository.getLocalBasket()
                .collect(){
                    _basket.postValue(it)
                }
        }
    }

    fun deleteBasket(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllBasketDB()
            _basket.postValue(listOf())
        }
    }

    fun deleteSingleBasket(){
        viewModelScope.launch(Dispatchers.IO) {
            val selected = _selectedBasket.value
            if (selected != null){
                repository.deleteSingleBasketDB(selected)
                _selectedBasket.postValue(null)
                repository.getLocalBasket()
                    .collect(){
                        _basket.postValue(it)
                    }
            }
        }
    }



//    private fun getNewItemEntry(
//        title: String,
//        size: String,
//        bezelType: String,
//        price: String,
//        amount: String,
//        thumbnailUrl: String
//    ): Basket {
//        return Basket(
//            title = title,
//            size = size,
//            bezel = bezelType,
//            cost = price.toInt(),
//            amount = amount.toInt(),
//            thumbnailUrl = thumbnailUrl
//        )
//    }

//    fun addNewItem(
//        title: String,
//        size: String,
//        bezelType: String,
//        price: String,
//        amount: String,
//        thumbnailUrl: String
//    ) {
//        val newItem = getNewItemEntry(title, size, bezelType, price, amount, thumbnailUrl)
//        insertItem(newItem)
//    }

    private fun getArtPhotos() {

        viewModelScope.launch {
            _status.value = ArtApiStatus.LOADING

            try {
                _photos.value = ArtApi.retrofitService.getPhotos()
                _status.value = ArtApiStatus.DONE
            } catch (e: Exception) {
                _status.value = ArtApiStatus.ERROR
                _photos.value = listOf()
            }

        }

    }

    private fun getArtArtist(id: Int) {
        viewModelScope.launch {
            try {
                _album.value = ArtApi.retrofitService.getAlbum(id)
                _artist.value = ArtApi.retrofitService.getArtist(_album.value!!.userId)

            } catch (e: Exception) {
                _album.value = ArtAlbum()
            }
        }

    }


    fun setCurrentPicture(picture: ArtPhoto) {
        _single_photo.value = picture
        getArtArtist(picture.albumId)


    }

    fun setSize(size: String) {
        _single_photo.value?.size = size
        updatePrice()
    }

    fun setArtist() {
        _single_photo.value?.artist = _artist?.value?.name.toString()
    }

    fun setBezel(bezel: String) {
        _single_photo.value?.bezel = bezel
        updatePrice()
    }

    fun printAllValues() {
        println("Title: ${_single_photo.value?.title}\nSize: ${_single_photo.value?.size}\nBezel: ${_single_photo.value?.bezel}")
    }

    fun insertPhotoBasket(artPhoto: ArtPhoto) {
        _photo_basket.value?.add(artPhoto)
        setArtist()
    }

    fun sumTotalPrice(): Int {
        val sumList = mutableListOf<Int>()
        _photo_basket.value?.forEach { sumList.add(it.cost) }
        return sumList.sum()
    }


    private fun updatePrice() {
        println("størrelse: ${_single_photo.value?.size}")
        var finalPrice = PRICE_PER_PHOTO
        when (_single_photo.value?.bezel) {
            "Sølvramme" -> finalPrice += 50
            "Gullramme" -> finalPrice += 120
            else -> finalPrice += 0
        }
        when (_single_photo.value?.size) {
            "Medium" -> finalPrice += 80
            "Stor" -> finalPrice += 150
            else -> finalPrice += 0
        }
        _single_photo.value?.cost = finalPrice.toInt()
    }

}


class ArtViewModelFactory(private val basketRepository: BasketRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArtViewModel(basketRepository) as T
        }
        throw IllegalArgumentException("unknown ViewModel class")
    }
}




