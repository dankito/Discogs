package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.ThumbnailUrlToNullSerializer

@Serializable
data class ArtistReleasesResultItem(
    val id: Int,
    val artist: String,
    val title: String,
    val year: Int? = null,

    val type: SearchResultItemType,

    val role: String? = null,
    @SerialName("thumb")
    @Serializable(with = ThumbnailUrlToNullSerializer::class)
    val thumbnailUrl: String? = null,


    // master

    /**
     * Only set for [type] = [SearchResultItemType.Master].
     */
    @SerialName("main_release")
    val mainReleaseId: Int? = null,


    // release

    /**
     * The formats are separated by colon.
     */
    val format: String? = null,
    val label: String? = null,
    val status: String? = null,

    @SerialName("resource_url")
    val resourceUrl: String,
) {
    val isMaster: Boolean
        get() = type == SearchResultItemType.Master

    val isProbablyLive: Boolean
        get() = title.contains("Live At ", ignoreCase = true) || title.contains("Live In ", ignoreCase = true)
                || title.contains("Live From ", ignoreCase = true) || title.contains("Live On ", ignoreCase = true)
                || title.contains("Live Under ", ignoreCase = true) || title.contains("Live @ ", ignoreCase = true)
                || title.contains("(Live)", ignoreCase = true)

    override fun toString() = "${if (isMaster) "Master" else "Release"} $year $artist - $title"
}