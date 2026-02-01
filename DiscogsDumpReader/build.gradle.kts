plugins {
    alias(libs.plugins.kotlinJvm)
}


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
