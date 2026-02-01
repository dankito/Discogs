package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class MasterXmlEntity(
    @field:JacksonXmlProperty(isAttribute = true)
    val id: Int,

    val title: String,
    val year: Int,
    @field:JacksonXmlProperty(localName = "main_release")
    val mainRelease: Long,

    @field:JacksonXmlElementWrapper
    val artists: List<ArtistCredit> = emptyList(),

    @field:JacksonXmlElementWrapper
    val videos: List<Video> = emptyList(),

    @field:JacksonXmlElementWrapper
    val genres: List<String> = emptyList(),

    @field:JacksonXmlElementWrapper
    val styles: List<String> = emptyList(),

    val notes: String? = null,

    @field:JacksonXmlProperty(localName = "data_quality")
    val dataQuality: String? = null,
) {
    override fun toString() = "$title ($year)"
}