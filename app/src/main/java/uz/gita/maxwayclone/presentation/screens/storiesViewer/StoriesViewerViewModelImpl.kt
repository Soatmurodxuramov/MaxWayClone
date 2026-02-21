package uz.gita.maxwayclone.presentation.screens.storiesViewer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.domain.usecase.GetStoriesUseCase

class StoriesViewerViewModelImpl(private val getStoriesUseCase: GetStoriesUseCase, ): StoriesViewerViewModel , ViewModel() {

    override val loadingLiveData = MutableLiveData<Boolean>()
    override val successLiveData = MutableLiveData<List<StoriesResponseData>>()
    override val errorMessageLiveData = MutableLiveData<String>()

    override fun getStories() {
        getStoriesUseCase()
            .onStart {loadingLiveData.value = true}
            .onCompletion { loadingLiveData.value = false}
            .onEach { result ->
                result.onSuccess {
                    successLiveData.value = it
                    Log.d("TTT", "getStories: ${it}")
                }
                result.onFailure {
                    errorMessageLiveData.value =it.message?:"Unknown error"
                }

            }
            .launchIn(viewModelScope)
    }
}