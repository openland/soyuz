pluginManagement {
    repositories {
        jcenter()
        gradlePluginPortal()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
            }
        }
    }
}

enableFeaturePreview("GRADLE_METADATA")
