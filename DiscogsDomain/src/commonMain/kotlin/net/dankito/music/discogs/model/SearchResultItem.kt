package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResultItem(
    val type: SearchResultItemType,
    val id: Int,
    val title: String,

    @SerialName("thumb")
    val thumbnail: String? = null,
    @SerialName("cover_image")
    val coverImage: String? = null,


    // master and release

    val year: Int? = null,
    val country: String? = null,

    @SerialName("master_id")
    val masterId: Int? = null,
    @SerialName("master_url")
    val masterUrl: String? = null,

    val genre: List<String> = emptyList(),
    val style: List<String> = emptyList(),

    @SerialName("format")
    val formatNames: List<String> = emptyList(),
    val barcode: List<String> = emptyList(),
    @SerialName("label")
    val labels: List<String> = emptyList(),
    @SerialName("catno")
    val catalogNumber: String? = null,

    val community: Community? = null,


    // release

    @SerialName("format_quantity")
    val formatQuantity: Int? = null,
    val formats: List<Format> = emptyList(),


    // common again

    @SerialName("user_data")
    val userData: UserData? = null,

    val uri: String,
    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = "$type $title"
}