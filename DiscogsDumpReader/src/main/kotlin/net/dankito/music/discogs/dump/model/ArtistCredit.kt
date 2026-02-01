package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ArtistCredit(
    val id: Int,
    val name: String,
    val join: String = "",
    /**
     * It’s the exact way the artist name appears on that specific release (as printed on the label/cover), which may
     * differ from the artist’s canonical Discogs artist name.
     *
     * Example: canonical artist = The Beatles
     * On a particular release it might be printed as Beatles or The Beatle's → that printed form would be stored in anv.
     */
    @field:JacksonXmlProperty(localName = "anv")
    val artistNameVariation: String? = null,
) {
    override fun toString() = "$id $name"
}