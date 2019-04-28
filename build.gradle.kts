// import org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask

plugins {
    kotlin("multiplatform") version "1.3.30"
    id("kotlinx-serialization") version "1.3.30"
}

repositories {
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-dev") }
    maven { setUrl("https://dl.bintray.com/pgutkowski/Maven") }
}



kotlin {
    jvm()
    js()
    // wasm32("wasm")
//    val ios32 = iosArm32("ios32")
//    val ios64 = iosArm64("ios64")
//    val iosSim = iosX64("iosSim")
    sourceSets["commonMain"].dependencies {
        implementation(kotlin("stdlib-common"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.2.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.11.0")
        implementation("com.github.pgutkowski:kgraphql:0.3.0")
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["jvmMain"].dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.11.0")
        implementation("com.squareup.okhttp3:okhttp:3.6.0")
    }
    sourceSets["jvmTest"].dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit"))
    }
    sourceSets["jsMain"].dependencies {
        implementation(kotlin("stdlib-js"))
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.2.1")
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:0.11.0")
    }
    sourceSets["jsTest"].dependencies {
        implementation(kotlin("test-js"))
    }
//    sourceSets["ios32Main"].dependencies {
//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.11.0")
//    }
//    sourceSets["ios64Main"].dependencies {
//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.11.0")
//    }
//    sourceSets["iosSimMain"].dependencies {
//        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.11.0")
//    }
//
//
//    configure(listOf(ios32, ios64, iosSim)) {
//        binaries.framework {
//            baseName = "Soyuz"
//        }
//    }
//
//    tasks.create("debugFatFramework", FatFrameworkTask::class.java) {
//        baseName = "Soyuz"
//        from(
//            ios32.binaries.getFramework("DEBUG"),
//            ios64.binaries.getFramework("DEBUG"),
//            iosSim.binaries.getFramework("DEBUG")
//        )
//        destinationDir = buildDir.resolve("fat-framework/debug")
//        group = "Universal framework"
//        description = "Builds a universal (fat) debug framework"
//    }
//
//
//    tasks.create("releaseFatFramework", FatFrameworkTask::class.java) {
//        baseName = "Soyuz"
//        from(
//            ios32.binaries.getFramework("RELEASE"),
//            ios64.binaries.getFramework("RELEASE"),
//            iosSim.binaries.getFramework("RELEASE")
//        )
//        destinationDir = buildDir.resolve("fat-framework/release")
//        group = "Universal framework"
//        description = "Builds a universal (fat) release framework"
//    }
}

