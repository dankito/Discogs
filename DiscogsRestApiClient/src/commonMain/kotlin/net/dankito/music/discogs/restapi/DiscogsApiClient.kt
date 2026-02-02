package net.dankito.music.discogs.restapi

import net.dankito.music.discogs.model.Artist
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

    suspend fun getArtist(artistId: Long): WebClientResult<Artist> =
        get("/artists/$artistId")

    private suspend inline fun <reified T : Any> get(path: String): WebClientResult<T> {
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
        ))
    }

}