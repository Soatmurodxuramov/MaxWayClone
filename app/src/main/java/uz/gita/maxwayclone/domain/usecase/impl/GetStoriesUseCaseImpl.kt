package uz.gita.maxwayclone.domain.usecase.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.domain.repository.StoriesRepository
import uz.gita.maxwayclone.domain.usecase.GetStoriesUseCase

class GetStoriesUseCaseImpl(private val storiesRepository: StoriesRepository) : GetStoriesUseCase {
    override fun invoke(): Flow<Result<List<StoriesResponseData>>> = flow{
        emit(storiesRepository.storiesGet())
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)

}