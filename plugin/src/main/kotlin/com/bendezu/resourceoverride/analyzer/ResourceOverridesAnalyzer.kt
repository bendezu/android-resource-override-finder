package com.bendezu.resourceoverride.analyzer

import com.bendezu.resourceoverride.data.*
import java.nio.file.Path

class ResourceOverridesAnalyzer {

    fun analyze(projectInfo: ProjectInfo, librariesInfo: LibrariesInfo): OverridingReport {
        val projectResources = projectInfo.distinctResources
        val libResources = librariesInfo.distinctResources

        val overridedResIds = projectResources intersect libResources

        val overrides = overridedResIds.map {
            OverrideInfo(
                resId = it,
                libraryDefinition = findResourceInLibraries(librariesInfo, resId = it),
                projectDefenition = findResourceInProject(projectInfo, resId = it)
            )
        }
        return OverridingReport(overrides)
    }

    private fun findResourceInLibraries(librariesInfo: LibrariesInfo, resId: String): ResourceDefinition {
        var path: Path? = null
        val libInfo = librariesInfo.libraries.find { libInfo ->
            val folder = libInfo.resFolders.find { resFolder ->
                val resFile = resFolder.resFiles.find { res ->
                    res.definedResIds.any { it == resId }
                }
                path = resFile?.path
                resFile != null
            }
            folder != null
        }
        return ResourceDefinition(path = path!!)
    }

    private fun findResourceInProject(projectInfo: ProjectInfo, resId: String): ResourceDefinition {
        var path: Path? = null
        val moduleInfo = projectInfo.gradleModules.find { module ->
            val resFile = module.resFiles.find { res ->
                res.definedResIds.any { it == resId }
            }
            path = resFile?.path
            resFile != null
        }
        return ResourceDefinition(path = path!!)
    }
}