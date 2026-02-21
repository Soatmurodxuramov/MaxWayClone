package uz.gita.maxwayclone.data.sources.remote.response

data class GeneralResponse<T>(
    val message : String,
    val data : List<T>
)