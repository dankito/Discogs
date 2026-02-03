package net.dankito.music.discogs.restapi

import net.dankito.music.discogs.model.Artist
import net.dankito.music.discogs.model.ArtistReleasesResult
import net.dankito.music.discogs.model.ArtistReleasesResultItem
import net.dankito.music.discogs.model.Master
import net.dankito.music.discogs.model.Release
import net.dankito.music.discogs.model.SearchResult
import net.dankito.music.discogs.model.SearchResultItemType
import net.dankito.web.client.ContentTypes
import net.dankito.web.client.KtorWebClient
import net.dankito.web.client.RequestParameters
import net.dankito.web.client.WebClient
import net.dankito.web.client.WebClientResult

open class DiscogsApiClient(
    private val personalAccessToken: String? = null, // PAT is enough for read-only lookups
    private val userAgent: String = "DiscogsApiClient 1.0.0",
    private val webClient: WebClient = KtorWebClient(),
    private val baseUrl: String = "https://api.discogs.com"
) {

    companion object {
        const val MaxItemsPerPage = 500
    }


    suspend fun getArtist(artistId: Int): WebClientResult<Artist> =
        get("/artists/$artistId")

    suspend fun getArtistAsJson(artistId: Int): WebClientResult<String> =
        get("/artists/$artistId")


    suspend fun getAllArtistReleases(artistId: Int, sortDescending: Boolean = true): WebClientResult<ArtistReleasesResult> {
        val releases = mutableListOf<ArtistReleasesResultItem>()
        var page = 1
        val itemsPerPage = MaxItemsPerPage

        var response = getArtistReleases(artistId, sortDescending, page, itemsPerPage)

        while (response.successfulAndBodySet && releases.size < response.body!!.pagination.items) {
            releases.addAll(response.body!!.releases)

            if (releases.size >= response.body!!.pagination.items) {
                break
            }

            response = getArtistReleases(artistId, sortDescending, ++page, itemsPerPage)
        }

        return if (response.successfulAndBodySet) response.copyWithBody(ArtistReleasesResult(response.body!!.pagination, releases))
                else response
    }

    suspend fun getArtistReleases(artistId: Int, sortDescending: Boolean = true, page: Int = 1, itemsPerPage: Int = MaxItemsPerPage): WebClientResult<ArtistReleasesResult> =
        get("/artists/$artistId/releases", buildMap {
            put("sort", "year") // year, title or format
            put("sort_order", if (sortDescending) "desc" else "asc")

            put("page", page)
            put("per_page", itemsPerPage)
        })


    suspend fun getMaster(masterId: Int): WebClientResult<Master> =
        get("/masters/$masterId")

    suspend fun getRelease(releaseId: Int): WebClientResult<Release> =
        get("/releases/$releaseId")


    suspend fun searchArtists(artistName: String, page: Int = 1, itemsPerPage: Int = 50) =
        search(SearchResultItemType.Artist, query = artistName, page = page, itemsPerPage = itemsPerPage)

    suspend fun searchMasters(query: String, page: Int = 1, itemsPerPage: Int = 50) =
        search(query = query, type = SearchResultItemType.Master, page = page, itemsPerPage = itemsPerPage)

    suspend fun searchMasters(artistName: String? = null, releaseTitle: String? = null, trackTitle: String? = null, page: Int = 1, itemsPerPage: Int = 50) =
        search(artistName = artistName, title = releaseTitle, trackTitle = trackTitle, type = SearchResultItemType.Master, page = page, itemsPerPage = itemsPerPage)

    suspend fun searchReleases(query: String, page: Int = 1, itemsPerPage: Int = 50) =
        search(query = query, type = SearchResultItemType.Release, page = page, itemsPerPage = itemsPerPage)

    suspend fun searchReleases(artistName: String? = null, releaseTitle: String? = null, trackTitle: String? = null, page: Int = 1, itemsPerPage: Int = 50) =
        search(artistName = artistName, title = releaseTitle, trackTitle = trackTitle, type = SearchResultItemType.Release, page = page, itemsPerPage = itemsPerPage)

    suspend fun searchLabels(labelName: String, page: Int = 1, itemsPerPage: Int = 50) =
        search(type = SearchResultItemType.Label, query = labelName, page = page, itemsPerPage = itemsPerPage)

    suspend fun search(query: String, type: SearchResultItemType? = null, page: Int = 1, itemsPerPage: Int = 50) =
        search(type, query, page = page, itemsPerPage = itemsPerPage)

    suspend fun search(type: SearchResultItemType? = null,
                       /**
                        * Your search query. Example: nirvana
                        */
                       query: String? = null,
                       /**
                        * Search artist names. Example: nirvana
                        */
                       artistName: String? = null,
                       /**
                        * Search by combined “Artist Name - Release Title” title field. Example: nirvana - nevermind
                        */
                       title: String? = null,
                       /**
                        * Search release titles. Example: nevermind
                        */
                       releaseTitle: String? = null,
                       /**
                        * Search track titles. Example: smells like teen spirit
                        */
                       trackTitle: String? = null,
                       /**
                        * Search label names. Example: dgc
                        */
                       labelName: String? = null,
                       /**
                        * Search formats. Example: album
                        */
                       format: String? = null,
                       genre: String? = null, style: String? = null, country: String? = null, year: Int? = null,
                       page: Int = 1, itemsPerPage: Int = 50): WebClientResult<SearchResult> {
        val queryParameters = buildMap {
            putIfSet("type", type?.apiName)

            putIfSet("query", query)
            putIfSet("artist", artistName)
            putIfSet("release_title", releaseTitle)
            putIfSet("title", title)
            putIfSet("label", labelName)
            putIfSet("track", trackTitle)

            putIfSet("format", format)
            putIfSet("genre", genre)
            putIfSet("style", style)
            putIfSet("country", country)
            putIfSet("year", year)

            put("page", page)
            put("per_page", itemsPerPage)
        }

        return get("/database/search", queryParameters)
    }

    private fun <K, V : Any> MutableMap<K, V>.putIfSet(key: K, value: V?) {
        if (value != null) {
            put(key, value)
        }
    }


    private suspend inline fun <reified T : Any> get(path: String, queryParameters: Map<String, Any> = emptyMap()): WebClientResult<T> {
        val url = if (path.startsWith("http")) path else "$baseUrl$path"

        val headers = buildMap {
            // PAT header (optional for some endpoints, recommended to avoid stricter limits)
            personalAccessToken?.let { put("Authorization", "Discogs token=$it") }
        }

        return webClient.get(RequestParameters(
            url = url,
            responseClass = T::class,
            accept = ContentTypes.JSON,
            userAgent = userAgent,
            headers = headers,
            queryParameters = queryParameters
        ))
    }

}