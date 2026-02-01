package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import net.dankito.music.discogs.dump.serialization.XmlSerializationConfig

@JacksonXmlRootElement(localName = "name")
data class Reference(
    @param:JacksonXmlProperty(isAttribute = true)
    val id: Int,

    @param:JacksonXmlProperty(localName = XmlSerializationConfig.XmlTextElementName)
    @param:JacksonXmlText
    val value: String? = null, // sometimes, in very rare cases, the value is missing, currently for two group names
) {
    override fun toString() = "$id ${value ?: "<value is missing>"}"
}