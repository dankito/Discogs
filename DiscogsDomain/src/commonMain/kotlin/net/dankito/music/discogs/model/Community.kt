package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Community(
    val have: Int,
    val want: Int,

    val rating: Rating? = null,

    val submitter: User? = null,
    val contributors: List<User> = emptyList(),

    val status: String? = null,
    @SerialName("data_quality")
    val dataQuality: String? = null,
)