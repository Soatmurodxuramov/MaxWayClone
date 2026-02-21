package uz.gita.maxwayclone.data.sources.local

import android.content.Context
import android.content.SharedPreferences
import uz.gita.maxwayclone.app.App

object TokenManager {

    val tokenPref: SharedPreferences = App.instance.getSharedPreferences("TokenManager" , Context.MODE_PRIVATE)

    fun saveToken(value : String){
        tokenPref.edit().putString("token" , value).apply()
    }

    fun getToken() : String{
        return tokenPref.getString("token" , "").toString()
    }





}