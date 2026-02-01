package net.dankito.music.discogs.dump.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import net.codinux.log.logger
import net.dankito.music.discogs.dump.model.ArtistXmlEntity
import net.dankito.music.discogs.dump.model.LabelXmlEntity
import net.dankito.music.discogs.dump.model.MasterXmlEntity
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
        readDump(artistsDumpStream, "artist") { reader ->
            deserializeArtist(reader, artistDeserialized)
        }
    }

    protected open fun deserializeArtist(reader: XMLStreamReader, artistDeserialized: (ArtistXmlEntity) -> Unit) = try {
        xmlMapper.readValue(reader, ArtistXmlEntity::class.java)?.let { artist ->
            artistDeserialized(artist)
        }
    } catch (e: Throwable) {
        log.error(e) { "Could not deserialize artist at line ${reader.location.lineNumber}, column ${reader.location.columnNumber}" }
    }


    open fun readMasters(mastersDumpStream: InputStream, masterDeserialized: (MasterXmlEntity) -> Unit) {
        readDump(mastersDumpStream, "master") { reader ->
            deserializeMaster(reader, masterDeserialized)
        }
    }

    protected open fun deserializeMaster(reader: XMLStreamReader, masterDeserialized: (MasterXmlEntity) -> Unit) = try {
        xmlMapper.readValue(reader, MasterXmlEntity::class.java)?.let { master ->
            masterDeserialized(master)
        }
    } catch (e: Throwable) {
        log.error(e) { "Could not deserialize master at line ${reader.location.lineNumber}, column ${reader.location.columnNumber}" }
        throw e
    }


    open fun readLabels(labelsDumpStream: InputStream, labelDeserialized: (LabelXmlEntity) -> Unit) {
        readDump(labelsDumpStream, "label") { reader ->
            deserializeLabel(reader, labelDeserialized)
        }
    }

    protected open fun deserializeLabel(reader: XMLStreamReader, labelDeserialized: (LabelXmlEntity) -> Unit) = try {
        xmlMapper.readValue(reader, LabelXmlEntity::class.java)?.let { label ->
            labelDeserialized(label)
        }
    } catch (e: Throwable) {
        log.error(e) { "Could not deserialize master at line ${reader.location.lineNumber}, column ${reader.location.columnNumber}" }
        throw e
    }


    protected open fun readDump(dumpStream: InputStream, entityElementName: String, readEntity: (XMLStreamReader) -> Unit) {
        val input: InputStream = if (dumpStream is GZIPInputStream) dumpStream else GZIPInputStream(dumpStream)

        val reader = staxFactory.createXMLStreamReader(input)
        try {
            while (reader.hasNext()) {
                val event = reader.next()
                if (event == XMLStreamConstants.START_ELEMENT && reader.localName == entityElementName) {
                    readEntity(reader)
                }
            }
        } finally {
            runCatching { reader.close() }
        }
    }

}