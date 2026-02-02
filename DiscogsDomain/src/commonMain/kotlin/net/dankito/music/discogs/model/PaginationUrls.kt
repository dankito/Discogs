package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class PaginationUrls(
    val last: String? = null,
    val next: String? = null,
)