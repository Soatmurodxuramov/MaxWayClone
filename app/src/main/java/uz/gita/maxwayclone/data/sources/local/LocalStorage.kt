package uz.gita.maxwayclone.data.sources.local

import android.content.Context
import android.content.SharedPreferences
import uz.gita.maxwayclone.app.App

object LocalStorage {
    val localPref : SharedPreferences = App.instance.getSharedPreferences("LocalStorage" , Context.MODE_PRIVATE )

}