package uz.gita.myapplication.presentation.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.gita.maxwayclone.UiState
import uz.gita.maxwayclone.data.model.home.AdModel
import uz.gita.maxwayclone.domain.usecase.GetAdsUseCase
import uz.gita.maxwayclone.ui.screens.home.HomeViewModel

class HomeViewModelImpl(private val adsUseCase: GetAdsUseCase) : ViewModel(), HomeViewModel {
    override val loaderLiveData = MutableLiveData<Boolean>()
    override val adsLiveData = MutableLiveData<List<AdModel>>()
    override val errorLiveData = MutableLiveData<String>()

    override fun fetchAds() {
        adsUseCase()
            .onEach { state ->
                when (state) {
                    is UiState.Loading -> {
                        loaderLiveData.value = true
                    }
                    is UiState.Success -> {
                        loaderLiveData.value = false
                        adsLiveData.value = state.data
                    }
                    is UiState.Error -> {
                        loaderLiveData.value = false
                        errorLiveData.value = state.message
                    }
                }
            }
            .launchIn(viewModelScope)
    }}