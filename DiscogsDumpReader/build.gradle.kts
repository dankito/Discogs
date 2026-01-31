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
    testImplementation(kotlin("test"))
}
