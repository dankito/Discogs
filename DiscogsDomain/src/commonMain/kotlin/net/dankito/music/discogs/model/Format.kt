package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class Format(
    val name: String,
    val qty: String,
    val text: String? = null,

    val descriptions: List<String> = emptyList(),
) {
    override fun toString() = "$qty $name"
}