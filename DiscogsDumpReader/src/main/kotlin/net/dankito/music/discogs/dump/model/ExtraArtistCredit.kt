package net.dankito.music.discogs.dump.model

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty

data class ExtraArtistCredit(
    val id: Int,
    val name: String? = null,
    /**
     * It’s the exact way the artist name appears on that specific release (as printed on the label/cover), which may
     * differ from the artist’s canonical Discogs artist name.
     *
     * Example: canonical artist = The Beatles
     * On a particular release it might be printed as Beatles or The Beatle's → that printed form would be stored in anv.
     */
    @field:JacksonXmlProperty(localName = "anv")
    val artistNameVariation: String? = null,
    /**
     * Very rare but the role can be null.
     */
    val role: String? = null,

    /**
     * Specifies for which tracks this info applies like `"9, 10"` or `"1 to 8, 11, 12"`.
     */
    val tracks: String? = null,
) {
    override fun toString() = "$id $name"
}