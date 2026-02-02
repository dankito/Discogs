package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class Video(
    val title: String? = null,
    val description: String? = null,

    /**
     * If not known it gets set to `0`.
     */
    val duration: Int,
    val source: String? = null,
    val embed: Boolean,
) {
    override fun toString() = "$title ($duration)"
}