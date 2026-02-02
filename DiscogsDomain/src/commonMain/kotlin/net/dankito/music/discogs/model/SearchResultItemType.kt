package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.SearchResultItemTypeSerializer

@Serializable(with = SearchResultItemTypeSerializer::class)
enum class SearchResultItemType(val apiName: String) {
    Artist("artist"),

    Master("master"),

    Release("release"),

    Label("label"),
    ;


    companion object {
        val byApiName = entries.associateBy { it.apiName }

        fun forApiName(apiName: String): SearchResultItemType =
            byApiName[apiName]!!
    }
}