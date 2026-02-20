package uz.gita.maxwayclone.ui.screens.home

import androidx.lifecycle.LiveData
import uz.gita.maxwayclone.data.model.home.AdModel

interface HomeViewModel {
    val loaderLiveData: LiveData<Boolean>
    val errorLiveData: LiveData<String>
    val adsLiveData: LiveData<List<AdModel>>

    fun fetchAds()
}



