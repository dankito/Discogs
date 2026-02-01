package net.dankito.music.discogs.dump.service

import assertk.assertThat
import assertk.assertions.isGreaterThanOrEqualTo
import net.codinux.log.logger
import net.dankito.music.discogs.dump.test.TestData
import java.text.DecimalFormat
import kotlin.io.path.inputStream
import kotlin.test.Test

class DiscogsDumpReaderTest {

    private val underTest = DiscogsDumpReader()

    private val countFormat = DecimalFormat("#,###")

    private val log by logger()


    @Test
    fun readArtists() {
        var count = 0

        underTest.readArtists(TestData.ArtistsDumpFile.inputStream()) { artist ->
            count++

            if (count % 10_000 == 0) {
                log.info { "Read ${formatCount(count)} artists" }
            }
        }

        assertThat(count).isGreaterThanOrEqualTo(9_875_997)
    }


    private fun formatCount(count: Int): String = countFormat.format(count)

}