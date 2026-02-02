package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Master(
    val id: Int,
    val title: String,
    val year: Int,

    @SerialName("main_release")
    val mainReleaseId: Int,
    @SerialName("most_recent_release")
    val mostRecentReleaseId: Int,

    val artists: List<ArtistCredit> = emptyList(),

    @SerialName("tracklist")
    val trackList: List<Track> = emptyList(),

    val images: List<Image> = emptyList(),

    val videos: List<Video> = emptyList(),

    val genres: List<String> = emptyList(),
    val styles: List<String> = emptyList(),

    @SerialName("num_for_sale")
    val numberForSale: Int? = null,
    @SerialName("lowest_price")
    val lowestPrice: Double? = null,


    @SerialName("resource_url")
    val resourceUrl: String,
    @SerialName("main_release_url")
    val mainReleaseUrl: String,
    @SerialName("most_recent_release_url")
    val mostRecentReleaseUrl: String,
    @SerialName("versions_url")
    val versionsUrl: String,
    val uri: String,

    @SerialName("data_quality")
    val dataQuality: String,
) {
    override fun toString() = "$id $title"
}