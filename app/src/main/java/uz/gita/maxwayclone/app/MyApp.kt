package uz.gita.maxwayclone.app

import android.app.Application
import android.content.Context
import uz.gita.maxwayclone.data.repository_impl.StoriesRepositoryImpl

class MyApp : Application(){
    companion object{
         lateinit var instance : Context
             private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        StoriesRepositoryImpl.init()
    }
}