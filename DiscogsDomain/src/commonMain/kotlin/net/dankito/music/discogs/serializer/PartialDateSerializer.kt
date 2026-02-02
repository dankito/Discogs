package net.dankito.music.discogs.serializer

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.dankito.music.discogs.model.PartialDate

object PartialDateSerializer : KSerializer<PartialDate?> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("PartialDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): PartialDate? {
        val value = decoder.decodeString()
        if (value.isBlank()) { // sometimes date strings are empty like: "date": "",
            return null
        }

        return PartialDate.parse(value)
    }

    @OptIn(ExperimentalSerializationApi::class)
    override fun serialize(encoder: Encoder, value: PartialDate?) {
        if (value == null) {
            encoder.encodeNull()
        } else {
            encoder.encodeString(value.asString)
        }
    }

}