package uz.gita.maxwayclone.presentation.screens.storiesViewer

import androidx.lifecycle.LiveData
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData

interface StoriesViewerViewModel {


    val loadingLiveData: LiveData<Boolean>
    val successLiveData: LiveData<List<StoriesResponseData>>
    val errorMessageLiveData: LiveData<String>
    fun getStories()
}