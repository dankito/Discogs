package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ReleaseXmlEntity(
    @field:JacksonXmlProperty(isAttribute = true)
    val id: Int,
    @field:JacksonXmlProperty(isAttribute = true)
    val status: String,
    @field:JacksonXmlProperty(localName = "master_id")
    val masterId: MasterId? = null,

    val title: String,

    val country: String? = null,
    val released: PartialDate? = null,
    val notes: String? = null,

    @field:JacksonXmlElementWrapper
    val artists: List<ArtistCredit> = emptyList(),

    @field:JacksonXmlElementWrapper
    @field:JacksonXmlProperty(localName = "extraartists")
    val extraArtists: List<ExtraArtistCredit> = emptyList(),

    @field:JacksonXmlElementWrapper
    @field:JacksonXmlProperty(localName = "tracklist")
    val trackList: List<Track> = emptyList(),

    val series: String? = null,

    @field:JacksonXmlElementWrapper
    val formats: List<Format> = emptyList(),

    @field:JacksonXmlElementWrapper
    val videos: List<Video> = emptyList(),

    @field:JacksonXmlElementWrapper
    val identifiers: List<Identifier> = emptyList(),

    @field:JacksonXmlElementWrapper
    val genres: List<String> = emptyList(),

    @field:JacksonXmlElementWrapper
    val styles: List<String> = emptyList(),

    @field:JacksonXmlElementWrapper
    val labels: List<Label> = emptyList(),

    @field:JacksonXmlElementWrapper
    val companies: List<Company> = emptyList(),

    @field:JacksonXmlProperty(localName = "data_quality")
    val dataQuality: String? = null,
) {
    override fun toString() = "$id $title"
}