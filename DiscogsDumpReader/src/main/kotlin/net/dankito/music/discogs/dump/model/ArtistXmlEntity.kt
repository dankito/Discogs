package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ArtistXmlEntity(
    val id: Int,
    val name: String? = null,

    @field:JacksonXmlProperty(localName = "realname")
    val realName: String? = null,
    val profile: String? = null,

    @field:JacksonXmlProperty(localName = "data_quality")
    val dataQuality: String? = null,

    @field:JacksonXmlElementWrapper
    val urls: List<String> = emptyList(),

    @field:JacksonXmlElementWrapper(localName = "name")
    @field:JacksonXmlProperty(localName = "namevariations")
    val nameVariations: List<String> = emptyList(),

    val aliases: List<Reference> = emptyList(),

    val members: List<Reference> = emptyList(),

    val groups: List<Reference> = emptyList(),
) {
    override fun toString() = "$id $name"
}