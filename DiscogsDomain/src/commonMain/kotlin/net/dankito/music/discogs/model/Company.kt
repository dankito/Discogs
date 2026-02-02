package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.EmptyStringToNullSerializer

@Serializable
data class Company(
    val id: Int,
    val name: String,
    @SerialName("catno")
    @Serializable(with = EmptyStringToNullSerializer::class)
    val catalogNumber: String? = null,

    @SerialName("entity_type")
    val entityType: String? = null,
    @SerialName("entity_type_name")
    val entityTypeName: String? = null,

    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,

    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = "${entityTypeName ?: id} $name"
}