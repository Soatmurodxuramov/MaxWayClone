package uz.gita.maxwayclone.app

import android.app.Application
import uz.gita.maxwayclone.data.repositoryr_impl.RepositoryImpl
import uz.gita.maxwayclone.data.sources.local.room.AppDatabase
import uz.gita.maxwayclone.data.sources.remote.client.ApiClient

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        val dao = AppDatabase.getDatabase(this).getDao()
        val api = ApiClient.getProductApi()
        RepositoryImpl.init(api, dao)
    }
}