// These versions intentionally live in an applied script rather than the main
// build file or version catalog. A scanner must follow Gradle's script model (or
// consume Gradle's resolved graph) instead of searching only build.gradle.kts.
extra["scriptedVulnerableVersions"] = mapOf(
    "snakeyaml" to "1.29",
    "guice" to "4.2.2",
)
