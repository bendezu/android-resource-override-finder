package com.bendezu.resourceoverride.analyzer

import com.bendezu.resourceoverride.data.Configuration
import com.bendezu.resourceoverride.data.ResourceFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.stream.Stream
import kotlin.streams.toList

class ResFolderAnalyzer(private val config: Configuration) {

    fun analyze(resDir: File): List<ResourceFile> {
        return getResourceFiles(resDir)
            .map {
                ResourceFile(
                    path = it,
                    definedResIds = handleResourceFile(it)
                )
            }.toList()
    }

    private fun getResourceFiles(projectDir: File): Stream<Path> {
        return Files.find(
            projectDir.toPath(),
            Integer.MAX_VALUE,
            { filePath, _ ->
                val pathStr = filePath.toString()
                return@find pathStr.endsWith(".xml")
                        && !pathStr.contains("/build/")
                        && pathStr.contains("/res/")
            }
        )
    }

    private fun handleResourceFile(path: Path): List<String> {
        val pathStr = path.toString()
        return if (pathStr.contains("/values")) {
            findResourcesInXml(path)
        } else {
            listOf(path.fileName.toString().replace(".xml", ""))
        }
    }

    private fun findResourcesInXml(path: Path): List<String> {
        val fileContent = Files.readString(path)
        val pattern = config.resourceDefinitionPattern
        val matcher = pattern.matcher(fileContent)
        val matches = mutableListOf<String>()
        while(matcher.find()) {
            matches.add(matcher.group(2))
        }
        return matches
    }
}