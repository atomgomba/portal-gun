tasks.create("updateBuildId") {
    dependsOn(tasks["build"])

    val pwd = rootProject.projectDir
    val branch =
        System.getenv("GITHUB_REF") ?: "git rev-parse --abbrev-ref HEAD".execute(pwd)?.trim()
    val hash = System.getenv("GITHUB_SHA") ?: "git rev-parse --short HEAD".execute(pwd)?.trim()
    val buildId = "$hash-$branch"
    val buildNumber = System.getenv("GITHUB_RUN_ID") ?: "N/A"

    println("Build id: $buildId")
    println("Build number: $buildNumber")

    val xmlFile = File(project.projectDir, "src/main/res/values/build_id.xml")
    if (!xmlFile.exists()) {
        xmlFile.createNewFile()
    }
    val xml = """<?xml version="1.0" encoding="utf-8"?>
<!-- This is a generated file, please do not edit manually -->
<resources xmlns:tools="http://schemas.android.com/tools">
    <string tools:ignore="UnusedResources" name="build_id" translatable="false">$buildId</string>
    <string tools:ignore="UnusedResources" name="build_number" translatable="false">$buildNumber</string>
</resources>
"""
    xmlFile.writeText(xml)

    println("  File: ${xmlFile.path}")
}
