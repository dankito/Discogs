package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class ArtistReleasesResult(
    val pagination: Pagination,
    val releases: List<ArtistReleasesResultItem>,
) {
    override fun toString() = "${releases.size} of ${pagination.items} releases, $releases"
}