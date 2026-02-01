package net.dankito.music.discogs.dump.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import net.codinux.log.logger
import net.dankito.music.discogs.dump.model.PartialDate
import java.time.LocalDate

open class PartialDateDeserializer : StdDeserializer<PartialDate>(PartialDate::class.java) {

    companion object {
        // gramophone has been invented in 1889, Édouard-Léon Scott de Martinville’s phonautograph machine in March 1857
        const val MinAllowedYear = 1857

        val MaxAllowedYear = LocalDate.now().year + 1
    }

    protected val log by logger()


    override fun deserialize(parser: JsonParser, context: DeserializationContext): PartialDate? {
        val raw = parser.valueAsString?.trim().orEmpty()
        if (raw.isEmpty()) {
            return null
        }

        val parts = getParts(raw)
        if (parts.size !in 1..3) {
            log.error { "Invalid PartialDate format: '$raw' (expected YYYY, YYYY-MM, or YYYY-MM-DD)" }
            return null
        }

        val year = parseIntOrNull(parts[0], "year", raw, MaxAllowedYear, MinAllowedYear)
        if (year == null) {
            return null
        }

        val month = parts.getOrNull(1)?.let {
            parseIntOrNull(it, "month", raw, 12)
        }

        val day = parts.getOrNull(2)?.let {
            parseIntOrNull(it, "day", raw, 31)
        }

        return PartialDate(rawValue = raw, year = year, month = month, day = day)
    }


    protected open fun getParts(raw: String): List<String> =
        if (raw.contains('-') || raw.length == 4) {
            raw.split('-')
        }
        // actually invalid but often encountered that numbers aren't separated by dashes
        else if (raw.length == 8 && raw.all { it.isDigit() }) {
            listOf(raw.substring(0, 4), raw.substring(4, 6), raw.substring(6, 8))
        } else {
            emptyList()
        }

    protected open fun parseIntOrNull(token: String, fieldName: String, raw: String, maxAllowedValue: Int, minAllowedValue: Int = 1): Int? {
        val parsed = token.toIntOrNull()

        return if (parsed == null) {
            log.error { "Invalid $fieldName in PartialDate: '$raw'" }
            null
        } else if (parsed == 0) {
            null
        } else if (parsed < minAllowedValue) {
            log.error { "Invalid $fieldName value of '$parsed' in PartialDate: '$raw'" }
            null
        } else if (parsed > maxAllowedValue) {
            log.error { "Invalid $fieldName value of '$parsed' in PartialDate: '$raw'" }
            null
        } else {
            parsed
        }
    }

}