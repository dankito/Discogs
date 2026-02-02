package net.dankito.music.discogs.service

import net.dankito.music.discogs.model.ArtistCredit

class FormatService {

    companion object {
        fun joinArtists(artists: List<ArtistCredit>): String? =
            if (artists.isEmpty()) {
                null
            } else {
                artists.joinToString("") { (it.name ?: it.artistNameVariation ?: "") + artistJoinPhrase(it.join) }
            }

        fun artistJoinPhrase(join: String): String =
            if (join.isBlank()) {
                ""
            } else {
                " $join "
            }
    }

}