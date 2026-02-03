package net.dankito.music.discogs.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ThumbnailUrlToNullSerializer : KSerializer<String?> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("ThumbnailUrlToNull", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): String? {
        val value = decoder.decodeString()

        // sometimes, mostly for coverImage, the image url points to a single pixel named spacer.gif, filter that out
        return value.takeUnless { it.isBlank() || it.endsWith("/spacer.gif") }
    }

    override fun serialize(encoder: Encoder, value: String?) {
        encoder.encodeString(value ?: "")
    }

}