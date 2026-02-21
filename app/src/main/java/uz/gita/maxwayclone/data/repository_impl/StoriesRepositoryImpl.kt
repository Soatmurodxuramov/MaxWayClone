package uz.gita.maxwayclone.data.repository_impl

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import uz.gita.maxwayclone.data.ApiClient
import uz.gita.maxwayclone.data.sources.remote.api.StoriesApi
import uz.gita.maxwayclone.data.sources.remote.response.ErrorMessageResponse
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData
import uz.gita.maxwayclone.domain.repository.StoriesRepository
import kotlin.jvm.Throws

class StoriesRepositoryImpl private constructor(
    val storiesApi: StoriesApi,
    val gson: Gson
) : StoriesRepository {


    companion object{
        private lateinit var instance : StoriesRepository

        fun init(){
            if (!::instance.isInitialized){
                instance = StoriesRepositoryImpl(ApiClient.storiesApi , Gson())
            }
        }

        fun getInstance() : StoriesRepository = instance

    }
    override suspend fun storiesGet(): Result<List<StoriesResponseData>> {
         val response = storiesApi.getStories()
        return if (response.isSuccessful && response.body() != null){
            Result.success(response.body()?.data ?: emptyList())
        }else{
            val errorJson = response.errorBody()?.string()
            if (errorJson.isNullOrEmpty()) Result.failure(Throwable("Unknown error"))
            else{
                val errorMessage = gson.fromJson(errorJson , ErrorMessageResponse::class.java)
                Result.failure(Throwable(errorMessage.message))
            }
        }
    }
}