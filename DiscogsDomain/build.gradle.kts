import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinxSerialization)
}


ext["customArtifactId"] = "discogs-domain"
ext["projectDescription"] = "Domain model for Discogs REST API and dump reader"


kotlin {
    jvmToolchain(17)

    jvm()

    js {
        browser()
        nodejs()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "DiscogsRestAPI"
            isStatic = true
        }
    }


    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.core)

            api(libs.kmpDateTime)
        }
    }
}


if (File(projectDir, "../gradle/scripts/publish-dankito.gradle.kts").exists()) {
    apply(from = "../gradle/scripts/publish-dankito.gradle.kts")
}