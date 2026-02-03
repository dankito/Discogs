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

    @Test
    fun getArtistReleases() = runTest {
        val response = underTest.getAllArtistReleases(116345)

        val result = assertSuccess(response)
        assertThat(result.releases.size).isGreaterThanOrEqualTo(600) // assert that all pages have been retrieved
    }


    @Test
    fun getMaster() = runTest {
        val result = underTest.getMaster(10332)

        val master = assertSuccess(result)
        assertThat(master.title).isEqualTo("White Blood Cells")
    }


    @Test
    fun getRelease() = runTest {
        val result = underTest.getRelease(501660)

        val release = assertSuccess(result)
        assertThat(release.title).isEqualTo("White Blood Cells")
    }


    @Test
    fun searchArtists() = runTest {
        val result = underTest.searchArtists("David Bowie")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("David Bowie")
    }

    @Test
    fun searchMastersByQuery() = runTest {
        val result = underTest.searchMasters("The Verve Urban Hymns")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("The Verve - Urban Hymns")
    }

    @Test
    fun searchMastersByArtistNameAndReleaseTitle() = runTest {
        val result = underTest.searchMasters("The Verve", "Urban Hymns")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("The Verve - Urban Hymns")
    }

    @Test
    fun searchReleasesByQuery() = runTest {
        val result = underTest.searchReleases("The Verve Urban Hymns")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("The Verve - Urban Hymns")
    }

    @Test
    fun searchReleasesByArtistNameAndReleaseTitle() = runTest {
        val result = underTest.searchReleases("The Verve", "Urban Hymns")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("The Verve - Urban Hymns")
    }

    @Test
    fun searchLabels() = runTest {
        val result = underTest.searchLabels("Sympathy For The Record Industry")

        val searchResult = assertSuccess(result)
        assertThat(searchResult.results.size).isGreaterThanOrEqualTo(1)
        assertThat(searchResult.results.first().title).isEqualTo("The Verve - Urban Hymns")
    }


    private fun <T> assertSuccess(result: WebClientResult<T>): T {
        assertThat(result.successful, "Web response should return success but response was ${result.statusCode}: ${result.body}").isTrue()
        assertThat(result.body).isNotNull()

        return result.body!!
    }
}