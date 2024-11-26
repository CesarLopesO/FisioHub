plugins {
    kotlin("multiplatform")
    id("com.android.library")
}
kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }

    sourceSets {
        val commonMain by getting
        val androidMain by getting {
            kotlin.srcDir("src/androidMain/kotlin")
        }
        val iosMain by getting
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8" // Alinha o JVM target com o Java
    }
}

android {
    namespace = "com.app.fisiohub.shared"
    compileSdk = 34

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = 21
        //noinspection EditedTargetSdkVersion
        targetSdk = 34
    }
}
