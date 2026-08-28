plugins {
    application
    java
}

group = "com.example.sca"
version = "1.0.0"

application {
    mainClass = "com.example.sca.App"
}

repositories {
    mavenCentral()
}

// Deliberately keep dependency declarations and part of their version data out
// of this entrypoint so the fixture exercises real Gradle model resolution.
apply(from = "gradle/dependency-versions.gradle.kts")
apply(from = "gradle/vulnerable-dependencies.gradle.kts")

dependencyLocking {
    lockAllConfigurations()
}
