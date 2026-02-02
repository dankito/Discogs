package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchResult(
    val pagination: Pagination,
    val results: List<SearchResultItem> = emptyList(),
) {
    override fun toString() = "${results.size} of ${pagination.items} items, $results"
}