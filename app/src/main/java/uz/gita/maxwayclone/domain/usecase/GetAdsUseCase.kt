package uz.gita.maxwayclone.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.maxwayclone.UiState
import uz.gita.maxwayclone.data.model.home.AdModel

interface GetAdsUseCase {
    operator fun invoke(): Flow<UiState<List<AdModel>>>
}