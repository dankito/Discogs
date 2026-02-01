package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Label(
    @field:JacksonXmlProperty(isAttribute = true)
    val id: Int,
    @field:JacksonXmlProperty(isAttribute = true)
    val name: String,
    @field:JacksonXmlProperty(isAttribute = true, localName = "catno")
    val catalogNumber: String? = null,
) {
    override fun toString() = "$id $name"
}