package net.dankito.music.discogs.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    val page: Int,
    val pages: Int,
    @SerialName("per_page")
    val itemsPerPage: Int,
    val items: Int,
    val urls: PaginationUrls,
) {
    override fun toString() = "Page $page of $pages, in total $items items"
}