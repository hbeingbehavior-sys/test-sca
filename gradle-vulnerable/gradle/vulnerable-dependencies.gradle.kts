import org.gradle.api.artifacts.VersionCatalogsExtension

val fixtureCatalog = extensions
    .getByType<VersionCatalogsExtension>()
    .named("libs")

@Suppress("UNCHECKED_CAST")
val scriptedVulnerableVersions =
    extra["scriptedVulnerableVersions"] as Map<String, String>

fun scriptedCoordinate(alias: String, module: String): String =
    "$module:${scriptedVulnerableVersions.getValue(alias)}"

dependencies {
    // Direct controls with no known active vulnerabilities at these versions.
    // Neither library adds transitive dependencies to the resolved graph.
    add("implementation", fixtureCatalog.findBundle("catalog-safe-controls").get())

    // Direct vulnerable dependencies supplied as a version-catalog bundle.
    add("implementation", fixtureCatalog.findBundle("catalog-direct").get())

    // Direct vulnerable dependency assembled from a version in another script.
    add(
        "implementation",
        scriptedCoordinate("snakeyaml", "org.yaml:snakeyaml"),
    )

    // Dependencies that intentionally bring vulnerable transitive components.
    add(
        "implementation",
        fixtureCatalog.findBundle("catalog-transitive-carriers").get(),
    )
    add(
        "implementation",
        scriptedCoordinate("guice", "com.google.inject:guice"),
    )
}
