package uz.gita.maxwayclone.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.maxwayclone.data.sources.remote.response.GeneralResponse
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData

interface GetStoriesUseCase {

    operator fun invoke() : Flow<Result<List<StoriesResponseData>>>
}