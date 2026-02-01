package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Format(
    @field:JacksonXmlProperty(isAttribute = true)
    val name: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val qty: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val text: String? = null,

    @field:JacksonXmlElementWrapper
    val descriptions: List<String> = emptyList(),
) {
    override fun toString() = "$qty $name"
}