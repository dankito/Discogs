package net.dankito.music.discogs.restapi

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThanOrEqualTo
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import kotlinx.coroutines.test.runTest
import net.dankito.music.discogs.restapi.test.TestData
import net.dankito.web.client.WebClientResult
import kotlin.test.Test

class DiscogsApiClientTest {

    private val underTest = DiscogsApiClient(TestData.PersonalAccessToken)


    @Test
    fun getArtist_Person() = runTest {
        val result = underTest.getArtist(278763)

        val artist = assertSuccess(result)
        assertThat(artist.name).isEqualTo("Jack White (2)")
        assertThat(artist.realName).isEqualTo("John Anthony White né Gillis")
        assertThat(artist.images.size).isGreaterThanOrEqualTo(17)
        assertThat(artist.urls.size).isGreaterThanOrEqualTo(11)
        assertThat(artist.aliases.size).isGreaterThanOrEqualTo(8)
        assertThat(artist.nameVariations.size).isGreaterThanOrEqualTo(25)
        assertThat(artist.members).isEmpty()
        assertThat(artist.groups.size).isGreaterThanOrEqualTo(8)
    }

    @Test
    fun getArtist_Group() = runTest {
        val result = underTest.getArtist(3840)

        val artist = assertSuccess(result)
        assertThat(artist.name).isEqualTo("Radiohead")
        assertThat(artist.realName).isNull()
        assertThat(artist.nameVariations.size).isGreaterThanOrEqualTo(9)
        assertThat(artist.images.size).isGreaterThanOrEqualTo(22)
        assertThat(artist.urls.size).isGreaterThanOrEqualTo(12)
        assertThat(artist.aliases.size).isGreaterThanOrEqualTo(3)
        assertThat(artist.members.size).isGreaterThanOrEqualTo(5)
    }


    private fun <T> assertSuccess(result: WebClientResult<T>): T {
        assertThat(result.successful, "Web response should return success but response was ${result.statusCode}: ${result.body}").isTrue()
        assertThat(result.body).isNotNull()

        return result.body!!
    }
}