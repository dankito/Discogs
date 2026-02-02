package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.EmptyStringToNullSerializer

@Serializable
data class Member(
    val id: Long,
    val name: String,
    val active: Boolean,

    @Serializable(with = EmptyStringToNullSerializer::class)
    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,

    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = "$id $name"
}