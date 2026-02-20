package uz.gita.maxwayclone.data.mapper

import uz.gita.maxwayclone.data.model.home.AdModel
import uz.gita.maxwayclone.data.sources.local.room.AdEntity
import uz.gita.maxwayclone.data.sources.remote.response.home.AdItemResponse


fun AdItemResponse.toEntity() = AdEntity(id = this.id, imageUrl = this.bannerUrl)

fun AdEntity.toDomain() = AdModel(id = this.id, imageUrl = this.imageUrl)