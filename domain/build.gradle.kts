plugins {
    kotlin("jvm") version "1.7.10"
}

group = "net.repeatuntil.wishly"
version = "1.0"

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}