package com.bendezu.resourceoverride.data

import java.nio.file.Path

data class OverridingReport(
    val overrides: List<OverrideInfo>
) {

    fun printReport() {
        if (overrides.isEmpty()) {
            println("\nOVERRIDES NOT FOUND")
        } else {
            println("\nFOUND ${overrides.size} OVERRIDES")
            overrides.forEach {
                val offset = "  "
                println("- ${it.resId}")
                println("${offset}Defined at ${it.projectDefenition.path}")
                println("${offset}Also presents at ${it.libraryDefinition.path}")
            }
        }
    }
}

data class OverrideInfo(
    val resId: String,
    val libraryDefinition: ResourceDefinition,
    val projectDefenition: ResourceDefinition
)

data class ResourceDefinition(
    val path: Path
)