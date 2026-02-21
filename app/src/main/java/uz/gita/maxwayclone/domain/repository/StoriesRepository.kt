package uz.gita.maxwayclone.domain.repository

import uz.gita.maxwayclone.data.sources.remote.api.StoriesApi
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData

interface StoriesRepository {
    suspend fun storiesGet() : Result<List<StoriesResponseData>>
}