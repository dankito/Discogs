package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val count: Int,
    val average: Double,
) {
    override fun toString() = "$average ($count votes)"
}