package net.dankito.music.discogs.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.dankito.music.discogs.model.SearchResultItemType

object SearchResultItemTypeSerializer : KSerializer<SearchResultItemType> {

    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("SearchResultItemType", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): SearchResultItemType {
        val apiName = decoder.decodeString()
        return SearchResultItemType.forApiName(apiName)
    }

    override fun serialize(encoder: Encoder, value: SearchResultItemType) {
        encoder.encodeString(value.apiName)
    }

}