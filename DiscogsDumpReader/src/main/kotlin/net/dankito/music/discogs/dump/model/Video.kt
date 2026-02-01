package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class Video(
    val title: String? = null,
    val description: String? = null,

    /**
     * If not known it gets set to `0`.
     */
    @field:JacksonXmlProperty(isAttribute = true)
    val duration: Int,
    @field:JacksonXmlProperty(isAttribute = true, localName = "src")
    val source: String,
    @field:JacksonXmlProperty(isAttribute = true)
    val embed: Boolean,
) {
    override fun toString() = "$title ($duration)"
}