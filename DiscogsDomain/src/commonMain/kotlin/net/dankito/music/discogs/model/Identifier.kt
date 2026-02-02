package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class Identifier(
    val type: String,
    val description: String? = null,
    val value: String? = null,
) {
    override fun toString() = "$type ${value ?: description}"
}