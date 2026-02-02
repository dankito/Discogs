package net.dankito.music.discogs.model

import kotlinx.serialization.Serializable
import net.dankito.music.discogs.serializer.PartialDateSerializer

/**
 * For a date month and/or day may not be set. So this class returns only the set parts.
 */
@Serializable(with = PartialDateSerializer::class)
data class PartialDate(
    val rawValue: String,

    val year: Int,
    val month: Int? = null,
    val day: Int? = null
) : Comparable<PartialDate> {

    companion object {
        val Null = PartialDate("0", 0)

        fun parse(partialDateString: String): PartialDate {
            val parts = partialDateString.split("-")

            val year = parts[0].toInt()
            val month = parts.getOrNull(1)?.toInt()
            val day = parts.getOrNull(2)?.toInt()

            return PartialDate(partialDateString, year, month, day)
        }
    }


    val asString: String by lazy {
        val year = year.toString().padStart(4, '0')

        if (month == null) {
            year
        } else if (day == null) {
            "$year-${month.toString().padStart(2, '0')}"
        } else {
            "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
        }
    }

    val sortableNumber: Long by lazy {
        // for sorting dates where a part is set should be ranked before dates where a part is missing
        year * 10000L + (month ?: 13) * 100L + (day ?: 32)
    }

    fun toIsoString(): String =
        "${year.toString().padStart(4, '0')}-${(month ?: 1).toString().padStart(2, '0')}-${(day ?: 1).toString().padStart(2, '0')}"

    override fun compareTo(other: PartialDate): Int =
        sortableNumber.compareTo(other.sortableNumber)

    override fun toString() = asString
}