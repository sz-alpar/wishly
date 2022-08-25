import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpack

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
}

group = "net.repeatuntil.wishly"
version = "1.0"

kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "shared"
            }
        }
    }
    js(IR) {
        browser {
            binaries.executable()

            webpackTask {
                outputFileName = "shared.js"
            }

            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13")
            }
        }
        val iosMain by getting
        val iosTest by getting
        val jsMain by getting {
            dependencies {
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(24)
        targetSdkVersion(29)
    }
}

val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
    val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    val targetDir = File(buildDir, "xcode-frameworks")
    from({ framework.outputDirectory })
    into(targetDir)
}

// TODO: copy built js to webApp

tasks.getByName("build") {
    dependsOn(packForXcode)
    dependsOn(tasks.getByName<KotlinWebpack>("jsBrowserProductionWebpack"))
}