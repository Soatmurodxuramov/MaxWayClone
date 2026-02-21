package uz.gita.maxwayclone.data.sources.remote.api

import retrofit2.Response
import retrofit2.http.GET
import uz.gita.maxwayclone.data.sources.remote.response.GeneralResponse
import uz.gita.maxwayclone.data.sources.remote.response.StoriesResponseData

interface StoriesApi {

    @GET("/stories")
    suspend fun getStories() : Response<GeneralResponse<StoriesResponseData>>
}