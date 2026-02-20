package uz.gita.maxwayclone.data.sources.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ads_table")
data class AdEntity(
    @PrimaryKey val id: Int,
    val imageUrl: String
)