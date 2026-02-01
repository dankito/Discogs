package net.dankito.music.discogs.dump.test

import kotlin.io.path.Path

object TestData {

    // TODO: set path to you discogs artists XML dump there. It has gzipped more than 450 MB and is therefore not included in the repository
    val ArtistsDumpFile = Path("build/dump/discogs_20260101_artists.xml.gz")

    // TODO: set path to you discogs masters XML dump there. It has gzipped more than 560 MB and is therefore not included in the repository
    val MastersDumpFile = Path("build/dump/discogs_20260101_masters.xml.gz")

}