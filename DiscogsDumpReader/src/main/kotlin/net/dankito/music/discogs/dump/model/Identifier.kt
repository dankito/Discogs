package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Identifier(
    @field:JacksonXmlProperty(isAttribute = true)
    val type: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val description: String? = null,
    @field:JacksonXmlProperty(isAttribute = true)
    val value: String? = null,
) {
    override fun toString() = "$type ${value ?: description}"
}