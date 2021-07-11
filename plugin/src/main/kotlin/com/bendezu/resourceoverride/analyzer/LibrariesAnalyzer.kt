package com.bendezu.resourceoverride.analyzer

import com.bendezu.resourceoverride.data.Configuration
import com.bendezu.resourceoverride.data.LibrariesInfo
import com.bendezu.resourceoverride.data.LibraryInfo
import com.bendezu.resourceoverride.data.LibraryResFolderInfo
import org.gradle.api.Project
import java.io.File
import java.nio.file.Files

class LibrariesAnalyzer(
    private val project: Project,
    private val config: Configuration,
    private val resFolderAnalyzer: ResFolderAnalyzer = ResFolderAnalyzer(config)
) {

    fun analyze(): LibrariesInfo {
        val libsDir = File(project.projectDir.path + "/.idea/libraries")
        val librariesSourcesInfo = libsDir
            .listFiles { it -> it.path.endsWith("aar.xml") }
            ?.map {
                LibraryInfo(
                    metadataPath = it.toPath(),
                    resFolders = collectResFoldersForLib(it)
                )
            } ?: emptyList()
        return LibrariesInfo(librariesSourcesInfo)
    }

    private fun collectResFoldersForLib(xmlFile: File): List<LibraryResFolderInfo> {
        val content = Files.readString(xmlFile.toPath())
        return getResPaths(content).mapNotNull {
            val resDir = File(it)
            if (resDir.exists()) {
                LibraryResFolderInfo(
                    dir = resDir.toPath(),
                    resFiles = resFolderAnalyzer.analyze(resDir)
                )
            } else null
        }
    }

    private fun getResPaths(text: String): List<String> {
        val pattern = config.xmlLibraryPathPattern
        val matcher = pattern.matcher(text)
        val matches = mutableListOf<String>()
        while(matcher.find()) {
            val resPath = matcher.group(1)
            matches.add(resPath.replaceEnvVariables())
        }
        return matches
    }

    private fun String.replaceEnvVariables(): String {
        return replace("\$USER_HOME\$", System.getenv("HOME"))
    }
}