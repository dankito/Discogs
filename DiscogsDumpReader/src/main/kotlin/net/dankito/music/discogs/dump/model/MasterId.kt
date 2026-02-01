package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText
import net.dankito.music.discogs.dump.serialization.XmlSerializationConfig

data class MasterId(
    @field:JacksonXmlProperty(isAttribute = true, localName = "is_main_release")
    val isMainRelease: Boolean,

    @param:JacksonXmlProperty(localName = XmlSerializationConfig.XmlTextElementName)
    @param:JacksonXmlText
    val masterId: Int,
) {
    override fun toString() = "$masterId (isMainRelease = $isMainRelease)"
}