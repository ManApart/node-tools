plugins {
    kotlin("multiplatform") version "1.9.22"
}

val kotlinWrappersVersion = "1.0.0-pre.674"

group = "org.manapart"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        nodejs()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(npm("bimg", "^0.1.0"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
    }
}