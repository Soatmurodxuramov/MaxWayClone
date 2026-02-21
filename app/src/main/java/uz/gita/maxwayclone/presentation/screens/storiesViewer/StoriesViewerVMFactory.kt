package uz.gita.maxwayclone.presentation.screens.storiesViewer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import uz.gita.maxwayclone.data.repository_impl.StoriesRepositoryImpl
import uz.gita.maxwayclone.domain.usecase.impl.GetStoriesUseCaseImpl

class StoriesViewerVMFactory : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return StoriesViewerViewModelImpl(GetStoriesUseCaseImpl(StoriesRepositoryImpl.getInstance())) as T
    }
}