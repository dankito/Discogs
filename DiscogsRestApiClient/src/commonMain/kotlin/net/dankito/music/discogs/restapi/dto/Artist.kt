package net.dankito.music.discogs.restapi.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.restapi.serializers.EmptyStringToNullSerializer

@Serializable
data class Artist(
    val id: Long,
    val name: String,
    @SerialName("realname")
    val realName: String? = null,
    @SerialName("namevariations")
    val nameVariations: List<String> = emptyList(),
    @Serializable(with = EmptyStringToNullSerializer::class)
    val profile: String? = null,

    // for bands
    val members: List<Member> = emptyList(),
    // for artists that are (also) in groups
    val groups: List<Member> = emptyList(),

    val images: List<Image> = emptyList(),

    val aliases: List<Alias> = emptyList(),

    @SerialName("resource_url")
    val resourceUrl: String,
    @SerialName("releases_url")
    val releasesUrl: String,
    val uri: String,
    val urls: List<String> = emptyList(),

    @SerialName("data_quality")
    val dataQuality: String,
) {
    override fun toString() = "$id $name"
}