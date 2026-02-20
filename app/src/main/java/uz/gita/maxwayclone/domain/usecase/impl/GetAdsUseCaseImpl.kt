package uz.gita.maxwayclone.domain.usecase.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.maxwayclone.UiState
import uz.gita.maxwayclone.data.model.home.AdModel
import uz.gita.maxwayclone.domain.repository.Repository
import uz.gita.maxwayclone.domain.usecase.GetAdsUseCase

class GetAdsUseCaseImpl  (
    private val repository: Repository
) : GetAdsUseCase {
    override fun invoke(): Flow<UiState<List<AdModel>>> {
        return repository.getAds()
    }
}