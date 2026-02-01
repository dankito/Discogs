package net.dankito.music.discogs.dump.serialization

object XmlSerializationConfig {

    /**
     * Workaround for Jackson bug that XML text elements cannot be deserialized with Kotlin class or Java records, see:
     * https://github.com/FasterXML/jackson-module-kotlin/issues/138#issuecomment-576425261
     */
    const val XmlTextElementName = "text"

}