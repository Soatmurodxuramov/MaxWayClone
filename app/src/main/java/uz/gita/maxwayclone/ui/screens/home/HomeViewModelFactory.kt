package uz.gita.myapplication.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.gita.maxwayclone.data.repositoryr_impl.RepositoryImpl
import uz.gita.maxwayclone.domain.usecase.impl.GetAdsUseCaseImpl

class HomeViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val repository = RepositoryImpl.getInstance()
        val useCase = GetAdsUseCaseImpl(repository)

        if (modelClass.isAssignableFrom(HomeViewModelImpl::class.java)) {
            return HomeViewModelImpl(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}