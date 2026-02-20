package uz.gita.maxwayclone.data.sources.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [AdEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getDao(): AdsDao
companion object{
    @Volatile
    private var instance: AppDatabase? = null
    fun getDatabase(context: Context): AppDatabase {
        return instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "ads_database"
            )

                .fallbackToDestructiveMigration()
                .build()
                .also { instance = it }
        }
    }
    }
}
