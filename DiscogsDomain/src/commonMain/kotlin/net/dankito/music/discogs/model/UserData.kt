package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserData(
    @SerialName("in_wantlist")
    val inWantlist: Boolean = false,
    @SerialName("in_collection")
    val inCollection: Boolean = false,
)