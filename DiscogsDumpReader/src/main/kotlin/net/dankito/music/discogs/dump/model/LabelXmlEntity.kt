package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class LabelXmlEntity(
    val id: Int,
    val name: String? = null,
    @field:JacksonXmlProperty(localName = "contactinfo")
    val contactInfo: String? = null,
    val profile: String? = null,

    @field:JacksonXmlElementWrapper
    val urls: List<String> = emptyList(),

    val parentLabel: Reference? = null,

    @field:JacksonXmlProperty(localName = "sublabels")
    val subLabels: List<Reference> = emptyList(),

    @field:JacksonXmlProperty(localName = "data_quality")
    val dataQuality: String? = null,
) {
    override fun toString() = "$id $name"
}