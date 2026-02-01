package net.dankito.music.discogs.dump.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import net.codinux.log.logger
import net.dankito.music.discogs.dump.model.ArtistXmlEntity
import net.dankito.music.discogs.dump.serialization.XmlSerializationConfig
import java.io.InputStream
import java.util.zip.GZIPInputStream
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import javax.xml.stream.XMLStreamReader

open class DiscogsDumpReader {

    private val xmlMapper = XmlMapper(
        JacksonXmlModule().apply {
            /**
             * Workaround for Jackson bug that XML text elements cannot be deserialized with Kotlin class or Java
             * records, so apply this as property name on all `@JacksonXmlText` properties.
             */
            setXMLTextElementName(XmlSerializationConfig.XmlTextElementName)
        }
    ).apply {
        findAndRegisterModules()

        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true)
    }

    protected val staxFactory: XMLInputFactory = XMLInputFactory.newFactory().apply {
        setProperty(XMLInputFactory.SUPPORT_DTD, false)
        setProperty("javax.xml.stream.isSupportingExternalEntities", false)
    }

    protected val log by logger()


    open fun readArtists(artistsDumpStream: InputStream, artistDeserialized: (ArtistXmlEntity) -> Unit) {
        val input: InputStream = if (artistsDumpStream is GZIPInputStream) artistsDumpStream else GZIPInputStream(artistsDumpStream)

        val reader = staxFactory.createXMLStreamReader(input)
        try {
            while (reader.hasNext()) {
                val event = reader.next()
                if (event == XMLStreamConstants.START_ELEMENT && reader.localName == "artist") {
                    deserializeArtist(reader)?.let { artistDeserialized(it) }
                }
            }
        } finally {
            runCatching { reader.close() }
        }
    }

    private fun deserializeArtist(reader: XMLStreamReader): ArtistXmlEntity? = try {
        xmlMapper.readValue(reader, ArtistXmlEntity::class.java)
    } catch (e: Throwable) {
        log.error(e) { "Could not deserialize artist at line ${reader.location.lineNumber}, column ${reader.location.columnNumber}" }
        null
    }

}