package com.bendezu.resourceoverride.analyzer

import com.bendezu.resourceoverride.data.Configuration
import com.bendezu.resourceoverride.data.ModuleInfo
import com.bendezu.resourceoverride.data.ProjectInfo
import org.gradle.api.Project

class ProjectAnalyzer(
    private val project: Project,
    private val config: Configuration,
    private val resFolderAnalyzer: ResFolderAnalyzer = ResFolderAnalyzer(config)
) {

    fun analyze(): ProjectInfo {
        val gradleModules = project.subprojects.map {
            ModuleInfo(
                name = it.name,
                dir = it.projectDir,
                resFiles = resFolderAnalyzer.analyze(it.projectDir) // TODO: pass res/ folder
            )
        }
        return ProjectInfo(gradleModules)
    }
}