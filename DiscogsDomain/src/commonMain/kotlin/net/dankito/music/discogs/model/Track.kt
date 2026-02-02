package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.service.FormatService

@Serializable
data class Track(
    val position: String? = null,
    val title: String? = null,
    val duration: String? = null,
    @SerialName("type_")
    val type: String? = null,

    /**
     * Additional artists to the album artist(s) on this track.
     */
    @SerialName("extraartists")
    val extraArtists: List<ArtistCredit> = emptyList(),

    /**
     * On compilations where the album artist is 'Various Artists' this list contains all artists of the track.
     */
    val artists: List<ArtistCredit> = emptyList(),

    @SerialName("sub_tracks")
    val subTracks: List<Track> = emptyList(),
) {
    val artistName: String? by lazy {
        if (extraArtists.isNotEmpty()) FormatService.joinArtists(extraArtists)
        else if (artists.isNotEmpty()) FormatService.joinArtists(artists)
        else null
    }
    override fun toString() = "$position $duration ${artistName?.let { "$it - " } ?: ""}$title"
}