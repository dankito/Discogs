package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.EmptyStringToNullSerializer

@Serializable
data class ArtistCredit(
    val id: Int,
    /**
     * For extraArtists [name] may be `null`, for artists never.
     */
    val name: String? = null,
    val join: String = "",
    /**
     * It’s the exact way the artist name appears on that specific release (as printed on the label/cover), which may
     * differ from the artist’s canonical Discogs artist name.
     *
     * Example: canonical artist = The Beatles
     * On a particular release it might be printed as Beatles or The Beatle's → that printed form would be stored in anv.
     */
    @SerialName("anv")
    @Serializable(with = EmptyStringToNullSerializer::class)
    val artistNameVariation: String? = null,

    /**
     * For extraArtists, it is an exception that [role] is `null`;
     * for artists, it is the normal case.
     */
    @Serializable(with = EmptyStringToNullSerializer::class)
    val role: String? = null,

    /**
     * Specifies for which tracks this info applies like `"9, 10"` or `"1 to 8, 11, 12"`.
     */
    @Serializable(with = EmptyStringToNullSerializer::class)
    val tracks: String? = null,

    @SerialName("thumbnail_url")
    val thumbnailUrl: String? = null,

    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = "${role ?: id} $name"
}