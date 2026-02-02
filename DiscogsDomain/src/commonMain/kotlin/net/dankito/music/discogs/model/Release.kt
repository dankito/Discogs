package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.datetime.OffsetDateTime

@Serializable
data class Release(
    val id: Int,
    val title: String,
    val year: Int,

    @SerialName("master_id")
    val masterId: Int? = null,
    @SerialName("master_url")
    val masterUrl: String? = null,

    val country: String? = null,
    val released: PartialDate? = null,
    @SerialName("released_formatted")
    // e.g. "03 Jul 2001"
    val releasedFormatted: String? = null,
    val notes: String? = null,

    @SerialName("date_added")
    val dateAdded: OffsetDateTime? = null,
    @SerialName("date_changed")
    val dateChanged: OffsetDateTime? = null,

//    val series: List<String> = emptyList(),

    @SerialName("artists_sort")
    val artistsSort: String? = null,

    val artists: List<ArtistCredit> = emptyList(),

    @SerialName("extraartists")
    val extraArtists: List<ArtistCredit> = emptyList(),

    @SerialName("tracklist")
    val trackList: List<Track> = emptyList(),

    @SerialName("thumb")
    val thumbnail: String? = null,

    val images: List<Image> = emptyList(),

    val videos: List<Video> = emptyList(),

    val genres: List<String> = emptyList(),
    val styles: List<String> = emptyList(),

    @SerialName("format_quantity")
    val formatQuantity: Int,
    val formats: List<Format> = emptyList(),

    val identifiers: List<Identifier> = emptyList(),

    val labels: List<Company> = emptyList(),

    val companies: List<Company> = emptyList(),

    @SerialName("estimated_weight")
    val estimatedWeight: Int? = null,

    @SerialName("num_for_sale")
    val numberForSale: Int? = null,
    @SerialName("lowest_price")
    val lowestPrice: Double? = null,
    @SerialName("blocked_from_sale")
    val blockedFromSale: Boolean = false,
    @SerialName("is_offensive")
    val isOffensive: Boolean = false,

    @SerialName("resource_url")
    val resourceUrl: String,
    val uri: String,

    val community: Community? = null,

    val status: String,
    @SerialName("data_quality")
    val dataQuality: String,
) {
    override fun toString() = "$id $title"
}