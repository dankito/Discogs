package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,

    @SerialName("resource_url")
    val resourceUrl: String,
) {
    override fun toString() = username
}