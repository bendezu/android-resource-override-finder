package com.bendezu.resourceoverride

import com.bendezu.resourceoverride.analyzer.LibrariesAnalyzer
import com.bendezu.resourceoverride.analyzer.ProjectAnalyzer
import com.bendezu.resourceoverride.analyzer.ResourceOverridesAnalyzer
import com.bendezu.resourceoverride.data.Configuration
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

abstract class FindResourceOverrideTask : DefaultTask() {

    @TaskAction
    fun findOverrides() {
        val config = Configuration()
        val isDebug = config.isDebug

        println("\nANALYSING PROJECT:")
        val projectAnalyzer = ProjectAnalyzer(project, config)
        val projectInfo = projectAnalyzer.analyze()

        if (isDebug) println(projectInfo.prettyString())

        val projectResourcesCount = projectInfo.distinctResources.count()
        println("PROJECT DEFINED RESOURCES FOUND: $projectResourcesCount")


        println("\nANALYSING LIBRARIES:")
        val librariesAnalyzer = LibrariesAnalyzer(project, config)
        val librariesInfo = librariesAnalyzer.analyze()

        if (isDebug) println(librariesInfo.prettyString())

        val librariesResourcesCount = librariesInfo.distinctResources.count()
        println("LIBRARIES DEFINED RESOURCES FOUND: $librariesResourcesCount")


        val overridesAnalyzer = ResourceOverridesAnalyzer()
        val report = overridesAnalyzer.analyze(projectInfo, librariesInfo)

        if (isDebug) println(report.prettyString())

        report.printReport()
    }

    private fun Any.prettyString() = toString()
        .replace(", ", ",\n")
        .replace("(", "(\n")
}
