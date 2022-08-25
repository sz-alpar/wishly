plugins {
    id("com.android.application") version "7.2.2"
    kotlin("android") version "1.7.10"
}

group = "net.repeatuntil.wishly"
version = "1.0"

android {
    compileSdk = 32
    defaultConfig {
        applicationId = "net.repeatuntil.androidApp"
        minSdk = 24
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
}