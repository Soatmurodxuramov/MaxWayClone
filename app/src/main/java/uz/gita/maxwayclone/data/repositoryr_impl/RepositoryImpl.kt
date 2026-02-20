package uz.gita.maxwayclone.data.repositoryr_impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.maxwayclone.UiState
import uz.gita.maxwayclone.data.mapper.toDomain
import uz.gita.maxwayclone.data.mapper.toEntity
import uz.gita.maxwayclone.data.model.home.AdModel
import uz.gita.maxwayclone.data.sources.local.room.AdsDao
import uz.gita.maxwayclone.data.sources.remote.api.ProductApi
import uz.gita.maxwayclone.domain.repository.Repository

class RepositoryImpl private constructor(
    private val productApi: ProductApi,
    private val dao: AdsDao
): Repository{
    companion object {
        private var instance: RepositoryImpl? = null


        fun init(productApi: ProductApi, dao: AdsDao) {
            if (instance == null) instance = RepositoryImpl(productApi, dao)
        }

        fun getInstance(): RepositoryImpl = instance!!
    }

    override fun getAds(): Flow<UiState<List<AdModel>>> = flow {
        emit(UiState.Loading)


        val localData = dao.getAllAds().firstOrNull()
        if (!localData.isNullOrEmpty()) {
            emit(UiState.Success(localData.map { it.toDomain() }))
        }

        try {

            val response = productApi.getAds()
            val entities = response.data.map { it.toEntity() }
            dao.insertAds(entities)
            emit(UiState.Success(entities.map { it.toDomain() }))

        } catch (e: Exception) {
            if (localData.isNullOrEmpty()) {
                emit(UiState.Error(e.localizedMessage ?: "Connection error"))
            }
        }
    }.flowOn(Dispatchers.IO)


}