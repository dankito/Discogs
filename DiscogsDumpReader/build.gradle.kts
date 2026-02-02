plugins {
    alias(libs.plugins.kotlinJvm)
}


ext["customArtifactId"] = "discogs-db-dump-reader"
ext["projectDescription"] = "Reads a Discogs DB dump"


kotlin {
    jvmToolchain(17)

    compilerOptions {
        javaParameters = true
    }
}


dependencies {
    implementation(project(":DiscogsDomain"))

    implementation(libs.jackson.xml)
    implementation(libs.jackson.kotlin)
    implementation(libs.jackson.dateTime)

    implementation(libs.klf)


    testImplementation(libs.kotlin.test)
    testImplementation(libs.assertk)

    testImplementation(libs.logback)
}


if (File(projectDir, "../gradle/scripts/publish-dankito.gradle.kts").exists()) {
    apply(from = "../gradle/scripts/publish-dankito.gradle.kts")
}
