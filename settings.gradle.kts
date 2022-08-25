pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "Wishly"


include(":androidApp")
include(":shared")

include("domain")
