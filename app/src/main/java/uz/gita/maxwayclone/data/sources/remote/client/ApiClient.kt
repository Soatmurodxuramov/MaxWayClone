package uz.gita.maxwayclone.data.sources.remote.client

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.maxwayclone.data.sources.remote.api.ProductApi

object ApiClient {
    private const val BASE_URL = "https://idalia-witting-unfractiously.ngrok-free.dev/"
    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }
    fun getProductApi(): ProductApi = retrofit.create(ProductApi::class.java)

}