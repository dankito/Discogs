package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Company(
    val id: Int,
    val name: String,

    @field:JacksonXmlProperty(localName = "entity_type")
    val entityType: Int,
    @field:JacksonXmlProperty(localName = "entity_type_name")
    val entityTypeName: String,
    @field:JacksonXmlProperty(localName = "resource_url")
    val resourceUrl: String? = null,

    @field:JacksonXmlProperty(isAttribute = true, localName = "catno")
    val catalogNumber: String? = null,
) {
    override fun toString() = "$id $name"
}