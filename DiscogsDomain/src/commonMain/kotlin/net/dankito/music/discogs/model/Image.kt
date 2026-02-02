package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    /**
     * "primary" / "secondary" etc.
     */
    val type: String,
    val width: Int,
    val height: Int,
    val uri: String,
    val uri150: String,
    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = "$type ${width}x$height $uri"
}