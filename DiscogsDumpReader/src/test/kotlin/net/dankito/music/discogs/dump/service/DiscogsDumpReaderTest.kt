package net.dankito.music.discogs.dump.service

import assertk.assertThat
import assertk.assertions.isGreaterThanOrEqualTo
import net.codinux.log.logger
import net.dankito.music.discogs.dump.test.TestData
import java.text.DecimalFormat
import java.util.concurrent.atomic.AtomicInteger
import kotlin.io.path.inputStream
import kotlin.test.Test

class DiscogsDumpReaderTest {

    private val underTest = DiscogsDumpReader()

    private val countFormat = DecimalFormat("#,###")

    private val log by logger()


    @Test
    fun readArtists() {
        val count = AtomicInteger(0)

        underTest.readArtists(TestData.ArtistsDumpFile.inputStream()) { artist ->
            trackCount(count)
        }

        assertThat(count.get()).isGreaterThanOrEqualTo(9_875_997)
    }


    @Test
    fun readMasters() {
        val count = AtomicInteger(0)

        underTest.readMasters(TestData.MastersDumpFile.inputStream()) { master ->
            trackCount(count)
        }

        assertThat(count.get()).isGreaterThanOrEqualTo(2_510_246)
    }


    @Test
    fun readLabels() {
        val count = AtomicInteger(0)

        underTest.readLabels(TestData.LabelsDumpFile.inputStream()) { master ->
            trackCount(count)
        }

        assertThat(count.get()).isGreaterThanOrEqualTo(61257)
    }


    private fun trackCount(count: AtomicInteger) {
        val count = count.incrementAndGet()

        if (count % 10_000 == 0) {
            log.info { "Read ${formatCount(count)} masters" }
        }
    }

    private fun formatCount(count: Int): String = countFormat.format(count)

}