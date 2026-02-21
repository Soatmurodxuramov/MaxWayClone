package uz.gita.maxwayclone.data.sources.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoriesResponseData(
    val id : String,
    val name : String,
    val url : String
    ) : Parcelable