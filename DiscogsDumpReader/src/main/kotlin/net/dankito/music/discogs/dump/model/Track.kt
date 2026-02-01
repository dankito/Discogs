package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Track(
    val position: String? = null,
    val title: String? = null,
    val duration: String? = null,

    @field:JacksonXmlElementWrapper
    val artists: List<ArtistCredit> = emptyList(),

    @field:JacksonXmlElementWrapper
    @field:JacksonXmlProperty(localName = "extraartists")
    val extraArtists: List<ExtraArtistCredit> = emptyList(),

    @field:JacksonXmlElementWrapper
    @field:JacksonXmlProperty(localName = "sub_tracks")
    val subTracks: List<Track> = emptyList(),
) {
    override fun toString() = "$position $duration $title"
}